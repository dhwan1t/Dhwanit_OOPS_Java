package Uni.OOPS_Lab.com.course;

public class main {
    public static void main(String[] args) {
        service.CourseService sc = new service.CourseService();

        try {
            model.Course c1 = new model.Course(101, "OOPS", 100, 50);
            model.Course c2 = new model.Course(102, "Boot", 70, 70);

            sc.addCourse(c1);
            sc.addCourse(c2);
            System.out.println("Both courses have been added.\n");

            System.out.println("Here are the course details:");
            c1.display();
            System.out.println();
            c2.display();
            System.out.println();

            model m = new model();
            model.Student s1 = m.new Student(1, "Dhwanit");
            model.Student s2 = m.new Student(2, "Himisha");

            sc.enrollStudent(c1.getCourseId(), s1);
            sc.enrollStudent(c2.getCourseId(), s2);

            if (c2.getEnrolledStudents() >= c2.getMaxSeats()) {
                throw new exception.CourseFullException("Course \"" + c2.getCourseName() + "\" is full. No seats available.");
            }

            int searchId = 999;
            boolean found = false;
            for (model.Course c : new model.Course[] { c1, c2 }) {
                if (c.getCourseId() == searchId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new exception.CourseNotFoundException("Couldn't find a course with ID " + searchId + ".");
            }

        } catch (exception.CourseFullException e) {
            System.out.println("\nOops! " + e.getMessage());
        } catch (exception.CourseNotFoundException e) {
            System.out.println("\nSorry! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nSomething went wrong: " + e.getMessage());
        }

        System.out.println("\nHere are the enrollment records:");
        sc.viewCourses();
    }
}
