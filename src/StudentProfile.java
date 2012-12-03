public class StudentProfile extends Profile{
   
   public String username;
   public String password;
   
   //Constructors
   public StudentProfile() { }
   
   public StudentProfile(String id, String pw) {
      username = id;
      password = pw;
   }
   
   //Accessors
   public String getID() {
      return username;
   }
   
   public String getPW() {
      return password;
   }
   
   //"Setters"
   public void setID(String id) {
      username = id;
   }
   
   public void setPW(String pw) {
      password = pw;
   }
   
}