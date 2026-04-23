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
            System.out.println("Inserting course...");
            String sql = "INSERT INTO courses (name, max_seats, enrolled) VALUES (?, ?, ?)";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, c.getCourseName());
                pstmt.setInt(2, c.getMaxSeats());
                pstmt.setInt(3, 0);

                int rows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rows);

                if (rows == 0) {
                    System.err.println("CRITICAL ERROR: Insert executed but 0 rows affected!");
                }
            } catch (SQLException e) {
                System.err.println("CRITICAL SQL ERROR during addCourse: " + e.getMessage());
                throw new RuntimeException("Database insert failed", e); // Bubble up to UI
            }
        }

        public List<model.Course> getAllCourses() {
            List<model.Course> courses = new ArrayList<>();
            String sql = "SELECT id, name, max_seats, enrolled FROM courses";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    courses.add(new model.Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("max_seats"),
                            rs.getInt("enrolled")
                    ));
                }
                System.out.println("Courses fetched: " + courses.size());
            } catch (SQLException e) {
                System.err.println("CRITICAL SQL ERROR during getAllCourses: " + e.getMessage());
                throw new RuntimeException("Database select failed", e);
            }
            return courses;
        }
    }

    // (StudentService and EnrollmentService omitted for brevity, but follow the same RuntimeException pattern)
}
