<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Uni.OOPS_Lab.com.course.model.Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Management System - View Courses</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 50px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f8f9fa;
            color: #333;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .no-data {
            text-align: center;
            color: #777;
            font-style: italic;
            margin-top: 20px;
            padding: 20px;
            background-color: #f8f9fa;
            border: 1px solid #ddd;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>All Courses</h2>

        <%
            List<Course> courses = (List<Course>) request.getAttribute("courses");
            if (courses == null || courses.isEmpty()) {
        %>
            <div class="no-data">No courses found.</div>
        <%
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Max Seats</th>
                        <th>Enrolled Students</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Course course : courses) {
                    %>
                        <tr>
                            <td><%= course.getCourseId() %></td>
                            <td><%= course.getCourseName() %></td>
                            <td><%= course.getMaxSeats() %></td>
                            <td><%= course.getEnrolledStudents() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            }
        %>

        <a href="index.jsp" class="back-link">&larr; Back / Dashboard</a>
    </div>
</body>
</html>
