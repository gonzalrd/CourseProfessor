public class TeacherProfile extends Profile {
   
   public String name;
   
   public String username;
   public String[] courses;
   
   //Constructors
   public TeacherProfile() { }
   
   public TeacherProfile(String n, String[] coursesTaught) {
      name = n;
      courses = coursesTaught;
   }
   
   //Accessors
   public String getName() {
      return name;
   }
   
   public String getID() {
      return username;
   }
   
   public String[] getCourses() {
      return courses;
   }
   
   //"Setters"
   public void setName(String n) {
      name = n;
   }
   
   public void setCourses(String[] c) {
      courses = c;
   }   
}
