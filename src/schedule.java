import java.util.ArrayList;


public class schedule{
	
	private ArrayList courses;
	private int creditHours;
	private int maxCreditHours;
	
	public schedule(){
		courses = new ArrayList<course>();
		creditHours = 0;
		maxCreditHours = 20;
	}
	public schedule(int maxCreditHours){
		this.maxCreditHours = maxCreditHours;
		courses = new ArrayList<course>();
		creditHours = 0;
	}
	
	public schedule(ArrayList<course> curr){
		for(course c : curr)
			courses.add(c);
		
	}
	
	public void addCourse(course toAdd){
		boolean add = true;
		for(int i = 0; i<courses.size();i++){
			char[] sameDays = ((course) courses.get(i)).getOverlaps(toAdd);
			int numConflicts = sameDays.length;
			if(numConflicts > 0){
				if(((course) courses.get(i)).getStartTime() == toAdd.getStartTime())
					add = false;
				if(((course) courses.get(i)).getEndTime() > toAdd.getStartTime())
					add = false;
			}
		} 
		if(add)
			courses.add(toAdd);
		else
			System.out.println("Conflict with another course!");
	}
	
	public void removeCourse(course toRemove){
		courses.remove(toRemove);
	}
	
}