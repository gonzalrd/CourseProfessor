import java.util.ArrayList;

//TODO: this needs to be changed, Id is an int and it will be set after a student logs in
public class StudentProfile extends Profile{
   
   private String username;
   private String password;
   private int id;
   private schedule studentSchedule;
   
   //Constructors
   public StudentProfile() {
	   this.id = -1;
	   this.username ="";
	   this.password="";
	   this.studentSchedule = new schedule();
   }
   
   public StudentProfile(String name, String pw, int id) {
	  this.username = name;
	  this.password = pw;
      this.id = id;
   }
   public boolean addToSchedule(course c){
	   return this.studentSchedule.addCourse(c);
   }
   public schedule getSchedule(){
	   return studentSchedule;
   }
   //Accessors
   public int getID() {
      return id;
   }
   
   public String getPW() {
      return password;
   }
   
   public String getName(){
	   return username;
   }
   
   //"Setters"
   public void setID(int id) {
      this.id = id;
   }
   
   public void setPW(String pw) {
	   this.password = pw;
   }
   
   public void setName(String name){
	   this.username = name;   
   }
   
   public void setSchedule(ArrayList<course> curr){
	   this.studentSchedule = new schedule(curr);
   }
}
   
