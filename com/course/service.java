package Uni.OOPS_Lab.com.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class service {

    public static class CourseService {

        public void addCourse(model.Course c) {
            System.out.println(
                "[SERVICE LOG] Inserting course: " +
                    c.getCourseName() +
                    ", seats: " +
                    c.getMaxSeats()
            );
            String sql =
                "INSERT INTO courses (name, max_seats, enrolled) VALUES (?, ?, ?)";

            try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, c.getCourseName());
                pstmt.setInt(2, c.getMaxSeats());
                pstmt.setInt(3, 0);

                int rows = pstmt.executeUpdate();
                System.out.println("[SERVICE LOG] Rows affected: " + rows);
            } catch (SQLException e) {
                System.err.println(
                    "[SERVICE ERROR] SQL Exception during addCourse!"
                );
                e.printStackTrace();
            }
        }

        public void enrollStudent(int courseId, model.Student s) {
            System.out.println(
                "[SERVICE LOG] Legacy enrollStudent called for courseId: " +
                    courseId
            );
            EnrollmentService es = new EnrollmentService();
            es.enrollStudent(s.getStudentId(), courseId);
        }

        public void viewCourses() {
            System.out.println("[SERVICE LOG] Legacy viewCourses called.");
            List<model.Course> courses = getAllCourses();
            System.out.println("Course Details:");
            for (model.Course c : courses) {
                c.display();
                System.out.println("-------------------------");
            }
        }

        public List<model.Course> getAllCourses() {
            System.out.println("[SERVICE LOG] Fetching all courses from DB...");
            List<model.Course> courses = new ArrayList<>();
            String sql = "SELECT id, name, max_seats, enrolled FROM courses";

            try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    courses.add(
                        new model.Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("max_seats"),
                            rs.getInt("enrolled")
                        )
                    );
                }
                System.out.println(
                    "[SERVICE LOG] Courses fetched: " + courses.size()
                );
            } catch (SQLException e) {
                System.err.println(
                    "[SERVICE ERROR] SQL Exception during getAllCourses!"
                );
                e.printStackTrace();
            }
            return courses;
        }
    }

    public static class StudentService {

        public void addStudent(String name) {
            System.out.println("[SERVICE LOG] Inserting student: " + name);
            String sql = "INSERT INTO students (name) VALUES (?)";

            try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, name);
                int rows = pstmt.executeUpdate();
                System.out.println("[SERVICE LOG] Rows affected: " + rows);
            } catch (SQLException e) {
                System.err.println(
                    "[SERVICE ERROR] SQL Exception during addStudent!"
                );
                e.printStackTrace();
            }
        }
    }

    public static class EnrollmentService {

        public void enrollStudent(int studentId, int courseId) {
            System.out.println(
                "[SERVICE LOG] Enrolling studentId: " +
                    studentId +
                    " into courseId: " +
                    courseId
            );
            String enrollSql =
                "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
            String updateCourseSql =
                "UPDATE courses SET enrolled = enrolled + 1 WHERE id = ?";

            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                try (
                    PreparedStatement enrollStmt = conn.prepareStatement(
                        enrollSql
                    );
                    PreparedStatement updateStmt = conn.prepareStatement(
                        updateCourseSql
                    )
                ) {
                    enrollStmt.setInt(1, studentId);
                    enrollStmt.setInt(2, courseId);
                    int enrollRows = enrollStmt.executeUpdate();
                    System.out.println(
                        "[SERVICE LOG] Enrollment insert rows affected: " +
                            enrollRows
                    );

                    updateStmt.setInt(1, courseId);
                    int updateRows = updateStmt.executeUpdate();
                    System.out.println(
                        "[SERVICE LOG] Course update rows affected: " +
                            updateRows
                    );

                    conn.commit();
                    System.out.println(
                        "[SERVICE LOG] Transaction committed successfully."
                    );
                } catch (SQLException e) {
                    conn.rollback();
                    System.err.println(
                        "[SERVICE ERROR] Enrollment failed, transaction rolled back!"
                    );
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.err.println(
                    "[SERVICE ERROR] SQL Exception during DB connection for enrollment!"
                );
                e.printStackTrace();
            }
        }
    }
}
