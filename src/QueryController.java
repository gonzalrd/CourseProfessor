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
	    	return null;
		
	    
	    //LOAD THE SCHEDULE
	   String loadSchedule = "SELECT * FROM COURSE WHERE cid IN (SELECT COURSE.cid FROM SCHEDULE S, COURSE C where S.cid = C.cid AND S.sid = "+curr.getID()+");";
	   curr.setSchedule(this.findCourses(loadSchedule));
		
		
		return curr;
	}
	

	/** Adds a new course into the students schedule that is kept track of in java and the database
	 * 
	 * @param c
	 * @throws SQLException
	 */
	public void addCourseToSchedule(course c , StudentProfile s) throws SQLException{
		stat.executeUpdate("INSERT INTO SCHEDULE VALUES(" + s.getID() +"," + c.getCourseId() + ");");
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
	
	public String getCourseFeedBack(course c){
		String feedback = "Need Query for this.";
		
		
		return feedback;
	}
	

	private  boolean[] daysOfWeeks(String [] days){
		boolean[] daysTF = {false, false, false, false, false};
		
		
		
		//TODO write implementation
		
		return daysTF;
	}

}
