import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Student(int studentID, String name, String email, String phoneNumber, String address) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}

class Course {
    private int courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private int maxCapacity;
    private List<Student> enrolledStudents;

    public Course(int courseID, String courseName, String instructor, int credits, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }



    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isCourseFull() {
        return enrolledStudents.size() >= maxCapacity;
    }
}

class Enrollment {
    private int enrollmentID;
    private int studentID;
    private int courseID;
    private String enrollmentDate;

    public Enrollment(int enrollmentID, int studentID, int courseID, String enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }
}

class CollegeManager {
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public CollegeManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    
    public void addCourse(Course course) {
        courses.add(course);
    }

    
    public boolean enrollStudentInCourse(int studentID, int courseID, String enrollmentDate) {
        Student student = findStudentById(studentID);
        Course course = findCourseById(courseID);

        if (student != null && course != null && !course.isCourseFull()) {
            Enrollment enrollment = new Enrollment(enrollments.size() + 1, studentID, courseID, enrollmentDate);
            enrollments.add(enrollment);
            course.getEnrolledStudents().add(student);
            return true;
        }

        return false;
    }

    
    private Student findStudentById(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    
    private Course findCourseById(int courseID) {
        for (Course course : courses) {
            if (course.getCourseID() == courseID) {
                return course;
            }
        }
        return null;
    }

    public void displayStudentsInCourse(int courseID) {
        Course course = findCourseById(courseID);
        if (course != null) {
            List<Student> enrolledStudents = course.getEnrolledStudents();
            System.out.println("Students enrolled in course " + course.getCourseName() + ":");
            for (Student student : enrolledStudents) {
                System.out.println(student.getName());
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void displayCoursesForStudent(int studentID) {
        Student student = findStudentById(studentID);
        if (student != null) {
            System.out.println(student.getName() + " is enrolled in the following courses:");
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudentID() == studentID) {
                    Course course = findCourseById(enrollment.getCourseID());
                    if (course != null) {
                        System.out.println(course.getCourseName());
                    }
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }
}

public class CollegeManagementSystem {
    public static void main(String[] args) {
        CollegeManager collegeManager = new CollegeManager();

        Student student1 = new Student(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Student student2 = new Student(2, "Jane Smith", "jane.smith@example.com", "987-654-3210", "456 Elm St");
        collegeManager.addStudent(student1);
        collegeManager.addStudent(student2);

        
        Course course1 = new Course(101, "Mathematics", "Prof. Johnson", 3, 30);
        Course course2 = new Course(102, "History", "Prof. Davis", 3, 25);
        collegeManager.addCourse(course1);
        collegeManager.addCourse(course2);

        
        collegeManager.enrollStudentInCourse(1, 101, "2023-10-03");
        collegeManager.enrollStudentInCourse(2, 101, "2023-10-04");
        collegeManager.enrollStudentInCourse(2, 102, "2023-10-05");

        
        collegeManager.displayStudentsInCourse(101);

        
        collegeManager.displayCoursesForStudent(2);
    }
}
