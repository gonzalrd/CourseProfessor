import java.sql.*;
import java.util.ArrayList;

public class QueryController {
//This class handles running the queries. the Interface will pass information to this class.
	private Connection conn; 
	private Statement stat;
	
	
	public QueryController() throws ClassNotFoundException, SQLException{
		//Initialize the information needed to create this 
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:FinalProject.db");
		stat = conn.createStatement();
	}
	
	

	/**This class handles setting up what happens after a student logs in. This includes getting the current schedule and
	 * setting that student to the login one.
	 * @throws SQLException 
	 */
	public StudentProfile loggedIn(String userName, String Password ) throws SQLException{
		//TODO: needs a query that gets all the courses a student is currently taking. if this student 
		//than just needs to initialize the schedule that all students will be added to
				
		StudentProfile curr = new StudentProfile();
		curr.setName(userName);
		curr.setPW(Password);
		
		//set the student that logged n
		ResultSet rs = stat.executeQuery("SELECT * FROM USER WHERE name=" + curr.getName() 
				 + " AND password= " + curr.getPW() + ";");
	    while (rs.next())
	    {
	    	curr.setID(rs.getInt("sid"));
	    }
	    rs.close();
	    
	    if(curr.getID()==-1)
	    	return curr;
		
	    
	    //LOAD THE SCHEDULE
	   String loadSchedule = "SELECT * FROM COURSE WHERE cid IN (SELECT COURSE.cid FROM SCHEDULE S, COURSE C where S.cid = C.cid AND S.sid = "+curr.getID()+");";
	   curr.setSchedule(this.findCourses(loadSchedule));
		
		
		return curr;
	}
	
	//helper method that finds and creates an arraylist of course given a qeury.
	
	
	
	//helper method that finds and creates an array list of course given a query.

	public ArrayList<course> findCourses(String query) throws SQLException{
		ArrayList<course> foundCourses = new ArrayList<course>();
		ResultSet rs = stat.executeQuery(query);
		while (rs.next()) {
			course fd = new course();
			fd.setCourseId(rs.getInt("cid"));
			fd.setCourseName(rs.getString("name"));
			fd.setDepartment(rs.getString("department"));
			fd.setCourseDescription(rs.getString("description"));
			fd.setStartTime(rs.getInt("beginTime"));
			fd.setEndTime( rs.getInt("endTime"));
			foundCourses.add(fd);
		}
		rs.close();
		for(course c : foundCourses)
			c.setDaysOfWeek(this.daysOfWeeks(c));
		
		return foundCourses;
	}
	
	/**runs the query that returns a list of courses given the 
	 * subject selected by the query.
	 * @param subject
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<course> searchBySubject(String subject) throws SQLException{
		//TODO: need to test this department
		subject =  "\"" + subject + "\"";
		
		String query = "SELECT * FROM COURSE WHERE DEPARTMENT = " + subject + ";";
		ArrayList<course> foundCourses = findCourses(query);
		
		
	
		return foundCourses;
		
	}
	
	/** Adds a new course into the students schedule that is kept track of in java and the database
	 * 
	 * @param c
	 * @throws SQLException
	 */
	public void addCourseToSchedule(StudentProfile s, course c) throws SQLException{
		stat.executeUpdate("INSERT INTO SCHEDULE VALUES(" + s.getID() +"," + c.getCourseId() + ");");
	}
	
	/** This is adds a new student giving information from the GUI
	 * and sets them as the student logged in
	 * @throws SQLException 
	 * 
	 */
	public void addNewStudent(String userName, String pw) throws SQLException{
		stat.executeUpdate("INSERT INTO USER VALUES(NULL," + userName + "," +pw+ ", \"2012\""+");");	
	}
	
	
	public ArrayList<course> searchbyProf(String profName) throws SQLException{
		profName =  "\"" + profName + "\"";
		String query = "SELECT * FROM COURSE WHERE cid IN (SELECT cid FROM TEACHES T, PROFESSOR P WHERE T.pid = P.pid AND P.name = " +profName + ");";
		return findCourses(query);
	}
	public ArrayList<course> findOpenClassesForCourseAndProf(course c, TeacherProfile p) throws SQLException{
		String query = "SELECT C.cid AS cid, C.name AS name, C.description AS description, C.department AS department, C.beginTime AS beginTime, C.endTime AS endTime FROM COURSE C JOIN TEACHES T ON T.cid = C.cid AND C.name = \""+c.getCourseName()+"\" AND T.pid = "+p.getID();
		return this.findCourses(query);
	}
	public ArrayList<TeacherProfile> professorsTeachingCourse(course c) throws SQLException{
		ArrayList<TeacherProfile> teaching = new ArrayList<TeacherProfile>();
		String query = "SELECT * FROM PROFESSOR WHERE pid IN (SELECT pid FROM TEACHES T, COURSE C where T.cid = C.cid AND C.cid = "+c.getCourseId()+");";
		ResultSet rs = stat.executeQuery(query);
		while (rs.next()) {
			TeacherProfile prof = new TeacherProfile();
			prof.setName(rs.getString("name"));
			prof.setID(rs.getInt("pid"));
			teaching.add(prof);
		}
		rs.close();
		ArrayList<course> temp;
		for(int i=0;i<teaching.size();i++){
			temp = new ArrayList<course>(this.findOpenClassesForCourseAndProf(c, teaching.get(i)));
			teaching.get(i).setCourses(temp);
		}
			//prof.setCourses(this.findOpenClassesForCourseAndProf(c, prof));
		
		return teaching;
	}
	public ArrayList<course> searchbyDepartment(String depName) throws SQLException{
		//TODO: need to test this department
		depName =  "\"" + depName + "\"";
	
		String query = "SELECT * FROM COURSE WHERE department=" + depName + ";";
		
		ArrayList<course> foundCourses = findCourses(query);
		
		return  foundCourses;
	}
	
	public ArrayList<TeacherProfile> teaches(String courseName) throws SQLException{
		
		
		ArrayList<TeacherProfile> teaches  = new ArrayList<TeacherProfile>();
		ResultSet rs = stat.executeQuery("SELECT * FROM PROFESSOR WHERE pid IN (SELECT pid FROM TEACHES T, COURSE C where T.cid = C.cid AND C.name = "+ courseName + ");");

	    while (rs.next()) {
		 
	    }
		return teaches;
		
	}
	
	public String getCourseFeedBack(course c) throws SQLException{
		// I think that should work for getting course feedback based on the courseID
		String feedback = "SELECT feedback FROM RATING WHERE cid =" + c.getCourseId() + ";";
		
		return feedback;
	}
	
	public String getProfFeedbackForCourse(course c, TeacherProfile p) throws SQLException{
		String feedback="";
		String feedbackQuery ="SELECT feedback, rating FROM RATING WHERE cid =" + c.getCourseId() + " AND pid =" + p.getID() + ";";
		ResultSet rs = stat.executeQuery(feedbackQuery);
		while(rs.next()){
			feedback+=rs.getString("feedback")+" ("+rs.getDouble("rating")+"/5.0)"+"\n";
		}
		return feedback;
		
	}

	public void addCourseFeedBack(course c, StudentProfile s, TeacherProfile t, double rating, String Feedback ) throws SQLException{
		int cid = c.getCourseId();
		int sid = s.getID();
		int pid = t.getID();
		
		Feedback = "\"" + Feedback + "\"";
		
		stat.executeUpdate("INSERT INTO RATING VALUES(null," + pid + "," + cid + "," + rating + ","  + Feedback + "," + sid  + ");");
		
	}

	private  boolean[] daysOfWeeks(course c) throws SQLException{
		boolean[] daysTF = {false, false, false, false, false};		
		ArrayList<String> daysValues = new ArrayList<String>();
		
		ResultSet rs = stat.executeQuery("SELECT day FROM CLASSDAYS WHERE cid =" + c.getCourseId() + ";");
		
		while (rs.next()) {
			daysValues.add(rs.getString("day"));
		}
		for (int i=0; i < daysValues.size();i++) {
			if (daysValues.get(i).equals("Monday")) {
				daysTF[0] = true;			
			}
			if (daysValues.get(i).equals("Tuesday")) {
				daysTF[1] = true;			
			}
			if (daysValues.get(i).equals("Wednesday")) { 
				daysTF[2] = true;			
			}
			if (daysValues.get(i).equals("Thursday")) { 
				daysTF[3] = true;			
			}
			if (daysValues.get(i).equals("Friday")) { 
				daysTF[4] = true;			
			}			
		}	
		
		return daysTF;
	}

}
