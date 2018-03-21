/**
 * Student
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/20/18
 *
 */

public class Student implements Person {
    static int nextID = 0;
    
    private String name;
    private int age;
    private String gender;
    private int id;
    
    /**
     * Constructs a new Student with the corresponding parameters as field values, and the next ID value (one greater
     * than the previously created students's id value).
     *
     * @param name Name to be assigned to this Student
     * @param age Age of this Student
     * @param gender Gender of this Student
     */
    public Student(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
        
        id = nextID;
        nextID++;
    }
    
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public int getID() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    
    /**
     * Returns a String representation of the Teacher object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return String.format("name: %s, id: %d", name, id);
    }
    
    public boolean equals(Student other) {
        return id == other.getID();
    }
}