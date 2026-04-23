package Uni.OOPS_Lab.com.course;

public class exception {
    public static class CourseFullException extends Exception{
        public CourseFullException(String msg){
            super(msg);
        }
    }
    public static class CourseNotFoundException extends Exception{
        public CourseNotFoundException(String msg){
            super(msg);
        }
    }
}
