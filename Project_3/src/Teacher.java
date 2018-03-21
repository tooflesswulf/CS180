/**
 * Teacher
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/20/18
 *
 */

public class Teacher implements Person {
    protected static int nextID = 0;
    private String name;
    private int age;
    private String gender;
    private int id;
    private Course[] courses;
    private int perCourseSalary;
    private int baseSalary;
    
    private int nCourses;
    
    /**
     * Constructs a new Teacher with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created teacher's id value). Set the Teacher's baseSalary to
     * 30,000 and perCourseSalary to 15,000 by default.
     *
     * @param name Name of the new Teacher to be added
     * @param age Age of the new Teacher to be added
     * @param gender Gender of the new Teacher to be added
     */
    public Teacher(String name, int age, String gender) {
        this(name, age, gender, 30000, 15000);
    }
    
    /**
     * Constructs a new Teacher with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created teacher's ID value).
     *
     * @param name Name of the new Teacher to be added
     * @param age Age of the new Teacher to be added
     * @param gender Gender of the new Teacher to be added
     * @param baseSalary Amount the Teacher must be paid
     * @param perCourseSalary Amount the Teacher must be paid for each course they lead
     */
    public Teacher(String name, int age, String gender, int baseSalary, int perCourseSalary) {
        id = nextID++;
        
        setName(name);
        setAge(age);
        setGender(gender);
        this.baseSalary = baseSalary;
        this.perCourseSalary = perCourseSalary;
        
        courses = new Course[8];
        nCourses= 0;
    }
    
    /**
     * Adds a course to the Teacher's array of courses taught. If the course array is full, then its size is doubled
     * the course is added. A Teacher may teach the same course more than once (like having multiple sections). If
     * course is null, nothing changes.
     *
     * This method should not modify the passed course object.
     *
     * @param course
     */
    public void addCourse(Course course) {
        if(nCourses == courses.length) {
            Course new_courses[] = new Course[2*courses.length];
            System.arraycopy(courses, 0, new_courses, 0, nCourses);
            
            courses = new_courses;
        }
        courses[nCourses] = course;
        nCourses++;
    }
    
    /**
     * Removes a course from the Teacher's array of courses taught. If the Teacher teaches multiple instances of the
     * course, only one is removed. Returns true if the course is found, false otherwise.
     *
     * @param course
     * @return boolean
     */
    public boolean dropCourse(Course course) {
        for(int i=0; i< nCourses; ++i) {
            if(courses[i] == course) {
                System.arraycopy(courses, i+1, courses, i, nCourses - i - 1);
    
                nCourses--;
                courses[nCourses] = null;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the number of times the Teacher teaches course. For example, if the course occurs twice this returns 2.
     * If the course is not taught by this Teacher, this returns 0. Returns 0 if course is null.
     *
     * @param course Course being confirmed if taught by teacher
     * @return Course object.
     */
    public int teachesCourse(Course course) {
        int counter = 0;
        for(int i=0; i<nCourses; ++i) {
            if(courses[i].equals(course)) counter++;
        }
        return counter;
    }
    
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public int getID() { return id; }
    public Course[] getCourses() { return courses; }
    public int getPerCourseSalary() { return perCourseSalary; }
    public int getBaseSalary() { return baseSalary; }
    
    public int getSalary() {
        return getBaseSalary() + getPerCourseSalary() * nCourses;
    }
    
    
    /**
     * Returns a String representation of the Teacher object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
    
    public boolean equals(Teacher other) {
        return id == other.getID();
    }
}