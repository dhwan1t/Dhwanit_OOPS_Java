package Uni.OOPS_Lab.com.course;

public class model {
    public static class Course {
        private int courseId;
        private String courseName;
        private int maxSeats;
        private int enrolledStudents;

        public Course(int courseId, String courseName, int maxSeats, int enrolledStudents) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.maxSeats = maxSeats;
            this.enrolledStudents = enrolledStudents;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getMaxSeats() {
            return maxSeats;
        }

        public void setMaxSeats(int maxSeats) {
            this.maxSeats = maxSeats;
        }

        public int getEnrolledStudents() {
            return enrolledStudents;
        }

        public void setEnrolledStudents(int enrolledStudents) {
            this.enrolledStudents = enrolledStudents;
        }

        public void display() {
            System.out.println("Course Id: " + courseId);
            System.out.println("Course Name: " + courseName);
            System.out.println("Max Seats: " + maxSeats);
            System.out.println("Enrolled Students: " + enrolledStudents);
        }
    }

    public class Student{
        int studentId;
        String studentName;
        Student(int studentId, String studentName){
            this.studentId = studentId;
            this.studentName = studentName;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public void display() {
            System.out.println("Student Id: " + studentId);
            System.out.println("Student Name: " + studentName);
        }
    }
}
