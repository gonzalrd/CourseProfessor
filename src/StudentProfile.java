import java.util.ArrayList;

//TODO: this needs to be changed, Id is an int and it will be set after a student logs in
public class StudentProfile extends Profile{
   
   private String username;
   private String password;
   private int id = -1;
   private schedule studentSchedule;
   
   //Constructors
   public StudentProfile() { }
   
   public StudentProfile(String name, String pw, int id) {
      username = name;
      password = pw;
      this.id = id;
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
      password = pw;
   }
   
   public void setName(String name){
	username = name;   
   }
   
   public void setSchedule(ArrayList<course> curr){
	   studentSchedule = new schedule(curr);
   }
}
   
