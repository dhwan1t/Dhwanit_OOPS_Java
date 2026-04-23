package Uni.OOPS_Lab.com.course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Uni.OOPS_Lab.com.course.model.Course;
import Uni.OOPS_Lab.com.course.service.CourseService;

public class CourseSwing extends JFrame {
    private CourseService courseService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName;
    private JTextField txtSeats;

    public CourseSwing() {
        courseService = new CourseService();
        setTitle("Course Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel: Add Course Form
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Name:"));
        txtName = new JTextField(15);
        topPanel.add(txtName);

        topPanel.add(new JLabel("Max Seats:"));
        txtSeats = new JTextField(5);
        topPanel.add(txtSeats);

        JButton btnAdd = new JButton("Add Course");
        btnAdd.addActionListener(e -> addCourse());
        topPanel.add(btnAdd);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel: Table
        String[] columns = {"ID", "Name", "Max Seats", "Enrolled"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel: Refresh Button
        JPanel bottomPanel = new JPanel();
        JButton btnRefresh = new JButton("Refresh / View Courses");
        btnRefresh.addActionListener(e -> refreshTable());
        bottomPanel.add(btnRefresh);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initial load
        refreshTable();
    }

    private void addCourse() {
        try {
            String name = txtName.getText().trim();
            int seats = Integer.parseInt(txtSeats.getText().trim());
            if (!name.isEmpty()) {
                Course c = new Course(0, name, seats, 0);
                courseService.addCourse(c);
                txtName.setText("");
                txtSeats.setText("");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid maximum seats number");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing data
        List<Course> courses = courseService.getAllCourses();
        for (Course c : courses) {
            Object[] row = {
                c.getCourseId(),
                c.getCourseName(),
                c.getMaxSeats(),
                c.getEnrolledStudents()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseSwing().setVisible(true);
        });
    }
}
