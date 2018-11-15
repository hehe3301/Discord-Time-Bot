package com.github.hehe3301;
import java.util.Scanner;

public class CliTestRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String name = in.nextLine().trim();
        String greeting = "Hello, %s!";
        System.out.println(String.format(greeting, name));
    }
}
