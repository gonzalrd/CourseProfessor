import java.sql.*;

public class QueryController {
//This class handles running the queries. the Interface will pass information to this class.
	  Statement stat;
	  StudentProfile curStudent; //this is the current student that is loged in that we will run the queries with
	  schedule studentSchdule;
	
	
	public QueryController() throws ClassNotFoundException, SQLException{
		//intialize the information needed to create this 
		Class.forName("org.sqlite.JDBC");
	    Connection conn =
	      DriverManager.getConnection("jdbc:sqlite:FinalProject.db");
	     stat = conn.createStatement();
	}
	
	/** This is adds a new student giving information from the gui 
	 * and sets them as the student Logined in
	 * @throws SQLException 
	 * 
	 */
	public void addNewStudent(String userName, String Password) throws SQLException{
		
		stat.executeUpdate("INSERT INTO USER(null," + userName + "," +Password + ", YEAR);");
		
	}

	/**This class handles setting up what happens after a student logs in. This includes getting the current schedule and
	 * setting that student to the login one.
	 */
	public void loggedIn(StudentProfile s , ){
		
		
	}
	
	public void couseToSchedule(course c , StudentProfile s){
		
		
		
	}
	

}
