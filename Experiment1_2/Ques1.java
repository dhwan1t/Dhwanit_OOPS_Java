package OOPS_Lab.Experiment1_2;

public class Ques1 {
    static class student{
        student(){
            System.out.println("Default constructor");
        }
        int id;
        String name;
        student(int id, String name){
            this.id = id;
            this.name = name;
        }

        public static void details(int id, String name){
            int uid = id;
            String Sname = name;
            System.out.println("Student name: "+ Sname);
            System.out.println("Student id: "+ uid);
        }
    }

    public static void main(String[] args) {
        student sobj = new student();
        sobj.details(404, "Ryan");
    }
}