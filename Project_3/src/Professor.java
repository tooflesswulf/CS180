/**
 * Professor
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/20/18
 *
 */

public class Professor extends Teacher {
    public Professor(String name, int age, String gender) {
        this(name, age, gender, 50000,15000);
    }
    
    public Professor(String name, int age, String gender, int baseSalary, int perCourseSalary) {
        super(name, age, gender, baseSalary, perCourseSalary);
    }
    
    public void addCourse(Course course) {
        if(teachesCourse(course) == 0) {
            super.addCourse(course);
        }
    }
    
    /**
     * Returns a String representation of the Teacher object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
}