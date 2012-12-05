import java.util.ArrayList;

public class TeacherProfile extends Profile {
   
   public String name;
   public int id;
   public ArrayList<course> courses;
   
   //Constructors
   public TeacherProfile() {
	   name = "";
	   id = -1;
	   courses = new ArrayList<course>();
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
   
   public String[] getCoursesAsStrings(){
	   String [] s = new String[this.courses.size()];
		for(int i = 0;  i < this.courses.size(); i++)
			 s[i] = this.courses.get(i).getDOW() + " ["
					 +this.courses.get(i).getStartTime()+"-"+this.courses.get(i).getEndTime()+"]";
					 
	   return s;
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
