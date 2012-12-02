public class course{

	private boolean daysOfWeek[] = {false,false,false,false,false};
	//Time is based on 24 hr clock
	private int startTime;
	private int endTime;
	private String courseName;
	private String courseDescription;
	
	
	public course(){
					//  M     T     W     R     F 
		startTime = 0;
		endTime = 0;
		courseName = "";
		courseDescription = "";
	}
	
	public course(String courseName, String courseDescription, int startTime, int endTime, boolean daysOfWeek[]){
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.startTime = startTime;
		this.endTime = endTime;
		for(int i=0;i<7;i++)
			this.daysOfWeek[i] = daysOfWeek[i];
	}
	
	//Get Data
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
		for(int i=0;i<7;i++){
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
	public void setEndTime(int e){this.endTime = e;}
	public void setCourseName(String s){this.courseName = s;}
	public void setCourseDescription(String s){this.courseDescription = s;}
	public void setDaysOfWeek(boolean[] d){
		for(int i=0;i<7;i++)
			this.daysOfWeek[i] = d[i];
	}
}