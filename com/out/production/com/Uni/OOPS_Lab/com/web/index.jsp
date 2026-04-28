<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Management System</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { max-width: 500px; margin: 0 auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #333; font-size: 1.2em; border-bottom: 1px solid #ccc; padding-bottom: 5px; }
        .form-group { margin-bottom: 10px; }
        label { display: block; margin-bottom: 5px; color: #666; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-top: 10px; }
        button:hover { background: #218838; }
        .view-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007bff; font-weight: bold; }
        .view-link:hover { text-decoration: underline; }
        hr { border: 0; height: 1px; background: #eee; margin: 20px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Course</h2>
        <form action="CourseServlet" method="POST">
            <div class="form-group">
                <label>Course Name:</label>
                <input type="text" name="courseName" required>
            </div>
            <div class="form-group">
                <label>Max Seats:</label>
                <input type="number" name="maxSeats" required min="1">
            </div>
            <button type="submit" name="action" value="addCourse">Add Course</button>
        </form>

        <hr>

        <h2>Add Student</h2>
        <form action="CourseServlet" method="POST">
            <input type="hidden" name="action" value="addStudent">
            <div class="form-group">
                <label>Student Name:</label>
                <input type="text" name="studentName" required>
            </div>
            <button type="submit">Add Student</button>
        </form>

        <hr>

        <h2>Enroll Student in Course</h2>
        <form action="CourseServlet" method="POST">
            <input type="hidden" name="action" value="enroll">
            <div class="form-group">
                <label>Student ID:</label>
                <input type="number" name="studentId" required min="1">
            </div>
            <div class="form-group">
                <label>Course ID:</label>
                <input type="number" name="courseId" required min="1">
            </div>
            <button type="submit">Enroll</button>
        </form>

        <a href="CourseServlet" class="view-link">View All Courses</a>
    </div>
</body>
</html>
