package Uni.OOPS_Lab.com.course;

import Uni.OOPS_Lab.com.course.exception.CourseFullException;
import Uni.OOPS_Lab.com.course.exception.CourseNotFoundException;
import Uni.OOPS_Lab.com.course.model.Course;
import Uni.OOPS_Lab.com.course.model.Student;
import Uni.OOPS_Lab.com.course.service.CourseService;

public class main {
    public static void main(String[] args) {
        CourseService sc = new CourseService();

        try {
            Course c1 = new Course(101, "OOPS", 100, 50);
            Course c2 = new Course(102, "Boot", 70, 70);

            sc.addCourse(c1);
            sc.addCourse(c2);
            System.out.println("Both courses have been added.\n");

            System.out.println("Here are the course details:");
            c1.display();
            System.out.println();
            c2.display();
            System.out.println();

            model m = new model();
            Student s1 = m.new Student(1, "Dhwanit");
            Student s2 = m.new Student(2, "Himisha");

            sc.enrollStudent(c1.getCourseId(), s1);
            sc.enrollStudent(c2.getCourseId(), s2);

            if (c2.getEnrolledStudents() >= c2.getMaxSeats()) {
                throw new CourseFullException("Course \"" + c2.getCourseName() + "\" is full. No seats available.");
            }

            int searchId = 999;
            boolean found = false;
            for (Course c : new Course[] { c1, c2 }) {
                if (c.getCourseId() == searchId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new CourseNotFoundException("Couldn't find a course with ID " + searchId + ".");
            }

        } catch (CourseFullException e) {
            System.out.println("\nOops! " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.out.println("\nSorry! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nSomething went wrong: " + e.getMessage());
        }

        System.out.println("\nHere are the enrollment records:");
        sc.viewCourses();
    }
}
