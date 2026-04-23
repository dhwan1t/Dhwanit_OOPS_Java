package Uni.OOPS_Lab.com.course;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

/*
 * To embed this applet in an HTML page, use the following snippet:
 * <applet code="Uni.OOPS_Lab.com.course.CourseApplet.class" width="400" height="150"></applet>
 *
 * Note: Applet is deprecated since Java 9. This is included only to fulfill the viva requirement.
 */
public class CourseApplet extends Applet {

    @Override
    public void paint(Graphics g) {
        // Use a readable font size (16pt)
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        g.setFont(font);

        // Display the text on screen
        g.drawString("Course Management System", 20, 40);
        g.drawString("Built with JDBC, Servlet, JSP, Swing & Applet", 20, 70);
        g.drawString("Applet is a legacy Java UI technology", 20, 100);
    }
}
