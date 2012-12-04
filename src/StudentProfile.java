//TODO: this needs to be changed, Id is an int and it will be set after a student logs in
public class StudentProfile extends Profile{
   
   public String username;
   public String password;
   public int id;
   
   //Constructors
   public StudentProfile() { }
   
   public StudentProfile(String name, String pw, int id) {
      username = name;
      password = pw;
      this.id = id;
   }
   
   //Accessors
   public int getID() {
      return id;
   }
   
   public String getPW() {
      return password;
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
   }
   
}