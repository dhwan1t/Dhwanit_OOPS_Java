package Uni.OOPS_Lab.com.course;

import java.io.*;
import java.util.ArrayList;

public class service {

    public static class CourseService{
        ArrayList<model.Course> cour = new ArrayList<>();
        String filen = "file.txt";

        public void addCourse(model.Course c){
            cour.add(c);
        }

        public void enrollStudent(int courseId, model.Student s){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(filen, true));

                writer.write("Course ID: " + courseId +
                        ", Student ID: " + s.getStudentId() +
                        ", Student Name: " + s.getStudentName());

                writer.newLine();
                writer.close();

                System.out.println("Student enrolled successfully!");

            }
            catch (IOException e){
                System.out.println("Error writing to file");
            }
        }
        public void viewCourses() {

            try{
                BufferedReader reader = new BufferedReader(new FileReader(filen));

                String line;

                System.out.println("Enrollment Details:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();

            }
            catch (IOException e){
                System.out.println("Error reading file");
            }
        }
    }
}
