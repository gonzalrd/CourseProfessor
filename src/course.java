public class course{

	private boolean daysOfWeek[] = {false,false,false,false,false};
	//Time is based on 2400 hr clock
	private int startTime;
	private int endTime;
	private String courseName;
	private String courseDescription;
	private String department;
	private int courseId;
	
	public course(){
					//  M     T     W     R     F 
		startTime = 0;
		endTime = 0;
		courseName = "";
		courseDescription = "";
		department = "";
		courseId = -1;
	}
	
	public course(String courseName, String courseDescription, int startTime, int endTime, 
			String department, int courseId, boolean daysOfWeek[]){
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.startTime = startTime;
		this.endTime = endTime;
		this.department = department;
		this.courseId = courseId;
		for(int i=0;i<5;i++)
			this.daysOfWeek[i] = daysOfWeek[i];
	}
	
	public course(course c){
		this.courseName = c.getCourseName();
		this.courseDescription = c.getCourseDescription();
		this.startTime = c.getStartTime();
		this.endTime = c.getEndTime();
		this.courseId = c.getCourseId();
		this.department = c.getDepartment();
		this.setDaysOfWeek(c.getDaysOfWeek());
	}
	
	//Get Data
	public String getDOW(){
		String s = "";
		for(int i=0;i<5;i++){
			if(this.daysOfWeek[i]==true){
				if(i==0)
					s+="M";
				else if(i==1)
					s+="T";
				else if(i==2)
					s+="W";
				else if(i==3)
					s+="R";
				else if(i==4)
					s+="F";
			}
		}
		return s;
	}
	public int getDuration(){return endTime-startTime;}
	public int getStartTime(){return startTime;}
	public int getEndTime(){return endTime;}
	public String getCourseName(){return courseName;}
	public String getCourseDescription(){return courseDescription;}
	public boolean[] getDaysOfWeek(){return daysOfWeek;}
	public char[] getOverlaps(course c){
		boolean[] cDays = c.getDaysOfWeek();
		char[] overlaps = new char[0];
		int counter = 0;
		for(int i=0;i<5;i++){
			if(this.daysOfWeek[i] == true && cDays[i] == true){
				switch(i){
				case 0:
					overlaps[counter] = 'M';
					break;
				case 1:
					overlaps[counter] = 'T';
					break;
				case 2:
					overlaps[counter] = 'W';
					break;
				case 3:
					overlaps[counter] = 'R';
					break;
				case 4:
					overlaps[counter] = 'F';
					break;
				}
				counter++;
			}
		}
		return overlaps;
	}
	
	//Set Data
	public void setStartTime(int s){this.startTime = s;}
	public void setCourseId(int id){this.courseId = id;}
	public void setDepartment(String dep){this.department = dep;}
	public String getDepartment(){return department;}
	public int getCourseId(){return this.courseId;}
	public void setEndTime(int e){this.endTime = e;}
	public void setCourseName(String s){this.courseName = s;}
	public void setCourseDescription(String s){this.courseDescription = s;}
	public void setDaysOfWeek(boolean[] d){
		for(int i=0;i<5;i++)
			this.daysOfWeek[i] = d[i];
	}
	
	public String toString(){
		String s = "";
		
		s = this.courseName +" " +getDOW() + " [" + getStartTime() + "-" + getEndTime() + "]";
		
		return s;
	}
}