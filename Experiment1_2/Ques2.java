package OOPS_Lab.Experiment1_2;

public class Ques2 {
    static class Area{
        void area(int l, int b) {
            System.out.println("Area of Rectangle = " + (l * b));
        }

        void area(double r) {
            System.out.println("Area of Circle = " + (3.14 * r * r));
        }
    }

    public static void main(String[] args) {
        Area ar = new Area();
        ar.area(7);
        ar.area(2, 4);
    }
}