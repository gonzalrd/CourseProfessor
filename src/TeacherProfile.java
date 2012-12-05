import java.util.ArrayList;

public class TeacherProfile extends Profile {
   
   public String name;
   public int id;
   public ArrayList<course> courses;
   
   //Constructors
   public TeacherProfile() {
	   name = "";
	   id = -1;
   }
   
   public TeacherProfile(String n, ArrayList<course> curr) {
      name = n;
      for(course c : curr)
    	  courses.add(c);
   }
   
   public TeacherProfile(TeacherProfile p){
	   this.name = p.getName();
	   this.id = p.getID();
	   this.setCourses(p.getCourses());
   }
   //Accessors
   public String getName() {
      return name;
   }
   
   public int getID() {
      return id;
   }
   
   public void setID(int id){
	   this.id = id;
   }
   
   public ArrayList<course> getCourses() {
      return courses;
   }
   
   //"Setters"
   public void setName(String n) {
      name = n;
   }
   
   public void setCourses(ArrayList<course> curr) {
	   for(course c : curr)
		   courses.add(c);
   }   
}
