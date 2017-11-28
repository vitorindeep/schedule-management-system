package schedulemanager.model;

/*
 * Class that represents a teacher who coordinates a Course
 */
public class Teacher extends User {
	
	private static final long serialVersionUID = 6095440121635306977L;
	
	private String courseManagedID; // ID of Course this teacher manages
	
	public Teacher(String id, String name, String password, String courseManagedID) {
		
		super(id, name, password);
		
		this.courseManagedID = courseManagedID;
	}
	
	public Teacher(Teacher t) {
		
		super(t);
		
		this.courseManagedID = t.getCourseManagedID();
	}
	
	public String getCourseManagedID() { return this.courseManagedID; }
	

        public Teacher clone() {
            return new Teacher(this);
    }
}
