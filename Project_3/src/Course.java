/**
 * Course
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/20/18
 *
 */

public class Course {
    /**
     * Maximum number of students allowed to be enrolled in a Course
     */
    protected final static int MAX_STUDENTS=100;
    
    private String name;
    private Professor professor;
    private Teacher[] teachers;
    private Student[] students;
    
    private int nTeachers;
    private int nStudents;
    
    /**
     * Constructs a Course object with the corresponding parameters as its name and a reference to the lead Professor.
     * Creates an array to contain at most 100 students enrolled in the course and ensures the Professor adds the course
     * to their list of courses.
     *
     * @param name      Name of the Course to be offered.
     * @param professor A reference to the lead professor for the course.
     */
    public Course(String name, Professor professor) {
        this.name=name;
        changeProfessor(professor);
        
        students = new Student[MAX_STUDENTS];
        teachers  = new Teacher[8];
        nStudents = 0;
        nTeachers = 0;
    }
    
    /**
     * Adds student to the course. Throws AddToCourseException if the course is full, if student is null, or
     * if the student is already enrolled in the course
     *
     * @param student Student to be added to the Course
     * @throws AddToCourseException If the course is full, if student is null, or if the student is already enrolled in
     *                              the course
     */
    public void addStudent(Student student) throws AddToCourseException {
        if (student == null) throw new AddToCourseException("Cannot add student `null`");
        if (nStudents >= 100) throw new AddToCourseException("Class is full.");
        
        for (Student s:students) {
            if (s==student) {
                throw new AddToCourseException("Student " + student.toString() + " is already enrolled.");
            }
        }
        
        students[nStudents] = student;
        nStudents++;
    }
    
    /**
     * Removes student from the course. Throws DropFromCourseException if student is null or if the student
     * is not enrolled in the course.
     *
     * @param student Student to be removed from the course
     * @throws DropFromCourseException If student is null or if the student is not enrolled in the course.
     */
    public void dropStudent(Student student) throws DropFromCourseException {
        if (student == null) throw new DropFromCourseException("Cannot drop student `null`");
        
        for (int i=0; i<nStudents; ++i) {
            if(students[i].equals(student)) {
                System.arraycopy(students, i+1, students, i, nStudents-i-1);
                nStudents--;
                students[nStudents] = null;
                return;
            }
        }
        
        throw new DropFromCourseException("Student " + " does not exist in this course.");
    }
    
    /**
     * Adds teacher to the course. If the teachers array is full, then its size is doubled and the teacher is added.
     * A Teacher may teach the same course more than once (like having multiple sections). Throws AddToCourseException
     * if teacher is null. The teacher should add this course to their personal list of courses.
     *
     * @param teacher Teacher to be added to the Course
     * @throws AddToCourseException If teacher is null
     */
    public void addTeacher(Teacher teacher) throws AddToCourseException {
        if (teacher==null) throw new AddToCourseException("Cannot add teacher `null`");
    
        if(nTeachers == teachers.length) {
            Teacher new_teachers[] = new Teacher[2*teachers.length];
            System.arraycopy(teachers, 0, new_teachers, 0, nTeachers);
        
            teachers = new_teachers;
        }
        teachers[nTeachers] = teacher;
        nTeachers++;
        
        teacher.addCourse(this);
    }
    
    /**
     * Removes teacher from the courses's array of teachers. If teacher teaches multiple instances of the
     * course, only one is removed. Throws DropFromCourseException if teacher is null or if teacher is not found.
     *
     * @param teacher Teacher to be added to the Course
     * @throws DropFromCourseException Uf teacher is null or if teacher is not found.
     */
    public void dropTeacher(Teacher teacher) throws DropFromCourseException {
        if (teacher==null) throw new DropFromCourseException("Cannot drop teacher `null`");
        
        for (int i=0; i<nTeachers; ++i) {
            if(teachers[i] == teacher) {
                teachers[i].dropCourse(this);
                System.arraycopy(teachers, i+1, teachers, i, nTeachers-i-1);
                nTeachers--;
                teachers[nTeachers] = null;
                return;
            }
        }
    
        throw new DropFromCourseException("Student " + " does not exist in this course.");
    }
    
    /**
     * @return Reference to Professor leading this Course
     */
    public Professor getProfessor() {
        return professor;
    }
    
    /**
     * Creates and returns a new array containing the list of Students. The new array should have a length equal to the
     * number of students currently enrolled in the course. Returns an array of length 0 if the course has no Students.
     *
     * @return A new array containing the Course's Students with no null elements.
     */
    public Student[] getRoster() {
        Student[] toRet = new Student[nStudents];
        System.arraycopy(students, 0, toRet, 0, nStudents);
        return toRet;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Returns a new array containing the Courses's Teachers. The new array should have a length equal to the
     * number of Teachers currently teaching the course, duplicates included. Returns an array of length 0 if the course
     * has no Teachers.
     *
     * @return A new array containing the Course's Teachers with no null elements.
     */
    public Teacher[] getTeachers() {
        Teacher[] toRet = new Teacher[nTeachers];
        System.arraycopy(teachers, 0, toRet, 0, nTeachers);
        return toRet;
    }
    
    /**
     * Makes professor the Course professor and updates the old and new Professors involved accordingly.
     *
     * @param professor Reference to Professor object to be made the lead Professor of the course.
     */
    public void changeProfessor(Professor professor) {
        if (this.professor != null) {
            this.professor.dropCourse(this);
        }
    
        this.professor=professor;
        if (this.professor != null) {
            this.professor.addCourse(this);
        }
    }
    
    /**
     * Returns a String representation of the Course object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
    
    public boolean equals(Course other) {
        return this.name.equals(other.name);
    }
}