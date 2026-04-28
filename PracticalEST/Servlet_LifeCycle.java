package Uni.OOPS_Lab.PracticalEST;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Servlet_LifeCycle {
    public static class HelloWorldServlet extends HttpServlet {

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            System.out.println("Servlet initialized");
        }

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            out.println("Hello World");
        }

        @Override
        public void destroy() {
            System.out.println("Servlet destroyed");
            super.destroy();
        }
    }
}
