package Uni.OOPS_Lab.PracticalEST;

import java.util.Scanner;
public class tryCatchFinally {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        try{
            int res = n/0;
            System.out.println("result: "+res);
        }
        catch (ArithmeticException e){
            System.out.println("Exception caught " + e.getMessage());
        }
        finally{
            System.out.println("Code did not break and ran successfully!");
        }
    }
}
