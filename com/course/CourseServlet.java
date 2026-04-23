package Uni.OOPS_Lab.com.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseServlet extends HttpServlet {

    private service.CourseService courseService = new service.CourseService();
    private service.StudentService studentService =
        new service.StudentService();
    private service.EnrollmentService enrollmentService =
        new service.EnrollmentService();

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response

    ) throws ServletException, IOException {
        System.out.println(
            "[SERVLET LOG] GET hit - Fetching courses to display on view.jsp"
        );
        System.out.println("Fetching courses...");

        try {
            List<model.Course> courseList = courseService.getAllCourses();
            request.setAttribute("courses", courseList);
            System.out.println("Passing courses to JSP: " + courseList.size());
            request.getRequestDispatcher("view.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println(
                "[SERVLET ERROR] Exception during GET processing:"
            );
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        System.out.println("POST HIT");
        System.out.println("POST hit");

        String action = request.getParameter("action");
        System.out.println(
            "[SERVLET LOG] Action parameter received: " + action
        );

        try {
            if ("addCourse".equals(action)) {
                String courseName = request.getParameter("courseName");
                String maxSeatsStr = request.getParameter("maxSeats");

                int maxSeats = 0;
                if (maxSeatsStr != null && !maxSeatsStr.trim().isEmpty()) {
                    maxSeats = Integer.parseInt(maxSeatsStr.trim());
                }

                System.out.println("courseName=" + courseName);
                System.out.println("maxSeats=" + maxSeats);
                System.out.println(
                    "Course: " + courseName + ", seats: " + maxSeats
                );

                if (
                    courseName != null &&
                    !courseName.trim().isEmpty() &&
                    maxSeats > 0
                ) {
                    model.Course newCourse = new model.Course(
                        0,
                        courseName,
                        maxSeats,
                        0
                    );
                    try {
                        Class.forName("org.sqlite.JDBC");
                        System.out.println("Driver loaded confirmation in servlet.");
                    } catch (ClassNotFoundException e) {
                        System.err.println("Driver load failed in servlet.");
                        e.printStackTrace();
                    }
                    System.out.println("Calling addCourse()");
                    courseService.addCourse(newCourse);
                    System.out.println(
                        "[SERVLET LOG] Successfully called addCourse on service."
                    );
                } else {
                    System.err.println(
                        "[SERVLET ERROR] Invalid course parameters. Name: " +
                            courseName +
                            ", Seats: " +
                            maxSeats
                    );
                }
            } else if ("addStudent".equals(action)) {
                String studentName = request.getParameter("studentName");
                System.out.println(
                    "[SERVLET LOG] Adding Student: " + studentName
                );

                if (studentName != null && !studentName.trim().isEmpty()) {
                    studentService.addStudent(studentName);
                } else {
                    System.err.println(
                        "[SERVLET ERROR] Student name is null or empty."
                    );
                }
            } else if ("enroll".equals(action)) {
                String studentIdStr = request.getParameter("studentId");
                String courseIdStr = request.getParameter("courseId");

                System.out.println(
                    "[SERVLET LOG] Enrolling Student ID: " +
                        studentIdStr +
                        " into Course ID: " +
                        courseIdStr
                );

                if (studentIdStr != null && courseIdStr != null) {
                    int studentId = Integer.parseInt(studentIdStr.trim());
                    int courseId = Integer.parseInt(courseIdStr.trim());
                    enrollmentService.enrollStudent(studentId, courseId);
                } else {
                    System.err.println(
                        "[SERVLET ERROR] Missing studentId or courseId."
                    );
                }
            } else {
                System.err.println(
                    "[SERVLET ERROR] Unknown or missing action: " + action
                );
            }
        } catch (Exception e) {
            System.err.println(
                "[SERVLET ERROR] Exception during POST processing:"
            );
            e.printStackTrace();
        }

        // PRG Pattern: Redirect to GET to prevent form resubmission
        System.out.println(
            "[SERVLET LOG] Redirecting to CourseServlet (GET flow)..."
        );
        response.sendRedirect("CourseServlet");
    }
}
