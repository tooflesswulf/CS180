/**
 * College
 *
 * Represents an abstraction of a college. Each college may have courses, professors, students, and teachers.
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class College {

    /**
     * Maximum number of Courses allowed to be added to a College
     */
    protected static final int MAX_COURSES = 10;

    /**
     * Maximum number of Professors allowed to be hired by a College
     */
    protected static final int MAX_PROFESSORS = 5;

    /**
     * Maximum number of Teachers allowed to be hired by a College
     */
    protected static final int MAX_TEACHERS = 25;

    /**
     * Maximum number of Students allowed to be enrolled to a College
     */
    protected static final int MAX_STUDENTS = 500;


    private String name;
    private Professor[] professors;
    private Teacher[] teachers;
    private Student[] students;
    private Course[] courses;
    private int tuition;
    
    private int nProfessors;
    private int nTeachers;
    private int nStudents;
    private int nCourses;

    /**
     * Constructs a new College object with the corresponding parameters as field values, and new arrays of Professors,
     * Students, Teachers, and Courses limited by the corresponding MAX variables.
     *
     * @param name Name of the College to be created.
     * @param tuition Cost for students to attend.
     */
    public College(String name, int tuition) {
        this.name = name;
        this.tuition = tuition;
        
        professors = new Professor[MAX_PROFESSORS];
        nProfessors = 0;
        
        teachers = new Teacher[MAX_TEACHERS];
        nTeachers = 0;
        
        students = new Student[MAX_STUDENTS];
        nStudents = 0;
        
        courses = new Course[MAX_COURSES];
        nCourses = 0;
    }

    /**
     * Adds course to the College's array of courses. If the course is null, the course is already available at the
     * College, or the College cannot add more courses, nothing changes.
     *
     * @param course A course object to be added to the College's array of courses.
     */
    public void addCourse(Course course) {
        if(course == null) return;
        if(nCourses == MAX_COURSES) return;
        
        for (int i=0; i<nCourses; ++i) {
            if (courses[i].equals(course)) return;
        }
        
        courses[nCourses] = course;
        nCourses++;
    }

    /**
     * Adds professor to the College's array of Professors. If the Professor is null, the College cannot add more
     * Professors, or the Professor is already employed by the College, nothing changes.
     *
     * @param professor Professor to be added to the College's array of Professors
     */
    public void hireProfessor(Professor professor) {
        if(professor == null) return;
        if(nProfessors == MAX_PROFESSORS) return;
    
        for (Professor p : professors) {
            if (p==professor) return;
        }
    
        professors[nProfessors] = professor;
        nProfessors++;
    }

    /**
     * Adds teacher to the College's array of Teachers. If teacher is null, the College cannot add more Teachers,
     * or teacher is already employed by the College, nothing changes.
     *
     * @param teacher Teacher to be added to the College's array of teachers
     */
    public void hireTeacher(Teacher teacher) {
        if(teacher == null) return;
        if(nTeachers == MAX_TEACHERS) return;
    
        for (Teacher p : teachers) {
            if (p==teacher) return;
        }
    
        teachers[nTeachers] = teacher;
        nTeachers++;
    }

    /**
     * Adds student to the College's array of students. If the student is null, the student is already enrolled at the
     * College, or the College cannot add more students, nothing changes.
     *
     * @param student A Student object to be added to the College's array of students.
     */
    public void addStudent(Student student) {
        if(student == null) return;
        if(nStudents == MAX_STUDENTS) return;
    
        for (Student s : students) {
            if (s==student) return;
        }
    
        students[nStudents] = student;
        nStudents++;
    }

    /**
     * Removes student from the College's array of Students. If the student is enrolled in any Courses, they should be
     * removed from these courses. If student is null or the student is not currently enrolled, nothing changes.
     *
     * *HINT* If you try to remove a student from a Course, an exception is thrown. Consider using a try-catch block.
     *
     * @param student Student to be removed from the College's array of students
     */
    public void dropStudent(Student student) {
        if (student==null) return;
        
        for (int i=0; i<nStudents; ++i) {
            if (students[i] == student) {
                for (Course c:courses) {
                    if (c==null) continue;
                    try { c.dropStudent(students[i]); } catch (DropFromCourseException e) {
                        // Do nothing.
                    }
                }
                
                System.arraycopy(students, i+1, students, i, nStudents-i-1);
                nStudents--;
                students[nStudents] = null;
                return;
            }
        }
    }

    /**
     * Returns the net change in the colleges budget. Tuition per student will increase the net change, and payments for
     * Professors and Teachers according to both their base and per course salaries will decrease the net change.
     *
     * @return Net change in the College's funds
     */
    public int calculateNetBudgetChange() {
        int tot_tuition = nStudents * tuition;
        
        int prof_salaries = 0;
        for (int i=0; i<nProfessors; ++i) {
            prof_salaries += professors[i].getSalary();
        }
        
        int teacher_salaries = 0;
        for (int i=0; i<nTeachers; ++i) teacher_salaries += teachers[i].getSalary();
        
        return tot_tuition - prof_salaries - teacher_salaries;
    }

    /**
     * @return A reference to the array of courses offered by this College.
     */
    public Course[] getCourses()
    {
        return courses;
    }

    /**
     * @return A reference to the array of Teachers employed by this College.
     */
    public Teacher[] getTeachers()
    {
        return teachers;
    }

    /**
     * @return A reference to the array of Professors employed by this College.
     */
    public Professor[] getProfessors()
    {
        return professors;
    }

    /**
     * @return A reference to the array of Students enrolled at this College.
     */
    public Student[] getStudents()
    {
        return students;
    }

    /**
     * @return The name of the College object
     */
    public String getName() {
        return name;
    }

    /**
     * @return The tuition charged per student
     */
    public int getTuition() {
        return tuition; // I wish
    }
}
