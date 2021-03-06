package schedulemanager.model;

import java.util.Collection;
import schedulemanager.db.ShiftDAO;
import java.io.Serializable;

/**
 * Represents a shift, which belongs to a course, which in turn belongs to a major.
 */
public class Shift implements Serializable {
    
    private static final long serialVersionUID = 7526472295622776147L;
    private String id;           // The ID of this shift. Example: "DSS:PL1"
    private String courseID;     // The ID of the course this shift belongs to. Example: "DSS"
    private int occupationLimit; // The maximum number of students allowed in this shift  
    private String teacher;
    private String classroom;
    private ShiftDAO shiftDAO;
    
    public Shift(String id, String courseID, int occupationLimit, String teacher, String classroom) {
    	 
    	// For ease of life when creating shifts, if the admin
    	// enters a short ID, fill it automatically
    	if (id.contains(":")) {
    		
    		this.id = id;
    	
    	} else {
    		    	
    		this.id = courseID + ":" + id;
    	}
        
    	this.courseID = courseID;
        this.occupationLimit = occupationLimit;
        this.teacher = teacher;
        this.classroom = classroom;
        this.shiftDAO = new ShiftDAO();
    }
    
    public Shift(Shift s) {
        this.id = s.getID();
        this.courseID = s.getCourseID();
        this.occupationLimit = s.getOccupationLimit();
        this.teacher = s.getTeacher();
        this.classroom = s.getClassroom();
        this.shiftDAO = new ShiftDAO();
    }
    
    public String getID() {
        return this.id;
    }
    
    public String getCourseID() {
        return this.courseID;
    }
    
    // Get a short ID
    // Given an id of "DSS:PL1", returns PL1
    public String getShortID() {
    	return this.id.split(":")[1];
    }
    
    public void setCourseID(String id) {
        this.courseID = id;
    }
    
    public int getOccupationLimit() {
        return this.occupationLimit;
    }
    
    public String getTeacher() {
        return this.teacher;
    }
    
    public String getClassroom(){
        return this.classroom;
    }

    public Collection<String> getOccupants() {
        return this.shiftDAO.getStudentsInShift(this.id);
    }
    
    public void addOccupant(Student s) {

    	this.shiftDAO.assignStudentToShift(s.getID(), this.id);
    }
   
    
    public String toString() {

        return this.id;
    }
    
    @Override
    public Shift clone() {
        return new Shift(this);
    }
}
