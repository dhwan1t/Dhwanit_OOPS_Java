package Uni.OOPS_Lab.Lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class random2 {

    public static void main(String[] args) throws IOException {
//        List<Integer> l1 = Arrays.asList(2, 6, 8, 10, 12, 14);
//
//        l1.stream()
//                .filter(n -> n>10)
//                .forEach(System.out::println);
//
//        List<Integer> filtered = l1.stream()
//                .filter(n -> n > 10).map(n -> n*2)
//                .collect(Collectors.toList());
//        System.out.println(filtered);
//        System.err.println("hello");
//
//        String s = "aman";
//        String b = "aman";
//        System.out.println(s == b);

        Scanner sc = new Scanner(System.in);
        File file = new File("filedem.txt");
        if(file.createNewFile()) System.out.println("filedem createa!");
        else System.out.println("didn't create any file, already exists");

        FileWriter fw = new FileWriter("filedem.txt");
        fw.write("Hello this should be in file 'filedem' ");
        fw.append("Will this work?");
        file.delete();
        fw.close();


    }
}
