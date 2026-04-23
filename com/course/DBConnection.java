package Uni.OOPS_Lab.com.course;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public static final String DB_PATH =
        System.getProperty("user.home") + File.separator + "courses.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    static {
        System.out.println("========== DB INIT START ==========");
        System.out.println("DB URL: " + URL);
        System.out.println(
            "DB absolute path: " + new File(DB_PATH).getAbsolutePath()
        );
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver loaded confirmation: org.sqlite.JDBC");
            initDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println(
                "CRITICAL ERROR: sqlite-jdbc.jar is MISSING from WEB-INF/lib!"
            );
            throw new RuntimeException(
                "SQLite Driver not found. Add sqlite-jdbc to WEB-INF/lib",
                e
            );
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        System.out.println("Connected: " + (conn != null));
        return conn;
    }

    private static void initDatabase() {
        String createCourses =
            "CREATE TABLE IF NOT EXISTS courses (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "max_seats INTEGER, " +
            "enrolled INTEGER DEFAULT 0);";

        try (
            Connection conn = getConnection();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(createCourses);
            System.out.println("Table 'courses' verified/created.");
        } catch (SQLException e) {
            System.err.println(
                "CRITICAL SQL ERROR during table creation: " + e.getMessage()
            );
            throw new RuntimeException(e);
        }
    }
}
