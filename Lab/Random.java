package Uni.OOPS_Lab.Lab;

class Demo{
    int num = 5;
    public String toString(){
        return "Hello";
    }
}
public class Random  extends Demo{
    int num = 10;
    public static void main(String[] args) {
        Demo d1 = new Demo();
        System.out.println(d1);
        System.out.println(d1.toString()); // toString method is called via compiler as an object
        String s1 = "Hello";
        String s2 = "Hell";
        System.out.println(d1.getClass().getName());

    }
}
