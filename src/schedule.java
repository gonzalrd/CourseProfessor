import java.util.ArrayList;


public class schedule{
	
	private ArrayList<course> courses = new ArrayList<course>();
	private int creditHours;
	private int maxCreditHours;
	
	public schedule(){
		this.creditHours = 0;
		this.maxCreditHours = 20;
	}
	public schedule(int maxCreditHours){
		this.maxCreditHours = maxCreditHours;
		this.creditHours = 0;
	}
	
	public schedule(ArrayList<course> curr){
		for(course c : curr)
			this.courses.add(c);
	}
	
	public String toString(){
		String s="";
		if(this.courses == null)
			s="No classes added.";
		else{
			for(int i = 0; i< this.courses.size(); i++)
				s += this.courses.get(i).toString()+"\n";
		}
		return s;
	}
	
	public boolean addCourse(course toAdd){
		boolean add = true;
		
		System.out.println(toAdd.toString());
		if(this.courses == null){
			this.courses.add(new course(toAdd));
			return true;
		}
		else{
			for(int i = 0; i<courses.size();i++){
				ArrayList<String> sameDays = courses.get(i).getOverlaps(toAdd);
				int numConflicts = sameDays.size();
				if(numConflicts > 0){
					if(courses.get(i).getStartTime() == toAdd.getStartTime())
						add = false;
					if(courses.get(i).getEndTime() > toAdd.getStartTime())
						add = false;
				}
			}
		
			if(add){
				this.courses.add(new course(toAdd));
				return true;
			}else
				return false;
		}
	}
	
	public void removeCourse(course toRemove){
		courses.remove(toRemove);
	}
	
}