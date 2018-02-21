package HW_01.src; /**
 * HW 01- BusinessCard
 *
 * This class prompts the user for some information, then prints it back out in the form of a business card.
 *
 * @author Albert Xu, L01
 *
 * @version 1/14/18
 *
 */

import java.util.Scanner;

public class BusinessCard {
    private String name;
    private int age;
    private double gpa;
    private String major;
    private String email;
    
    public BusinessCard() {}
    
    public void askUserInput() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Enter your name:");
        this.name = sc.nextLine();
    
        System.out.print("Enter your age:");
        this.age = sc.nextInt();
        sc.nextLine();
    
        System.out.print("Enter your GPA:");
        this.gpa = sc.nextDouble();
        sc.nextLine();
    
        System.out.print("Enter your major:");
        this.major = sc.nextLine();
    
        System.out.print("Enter your email:");
        this.email = sc.nextLine();
    
        System.out.println();
    }
    
    @Override
    public String toString() {
        return "Name:\t" + name +
               "\nAge:\t" + String.valueOf(age) +
               "\nGPA:\t" + String.format("%.2f", gpa) +
               "\nMajor:\t" + major +
               "\nEmail:\t" + email;
    }
    
    public static void main(String[] args) {
        BusinessCard bc = new BusinessCard();
        bc.askUserInput();
        System.out.println(bc);
    }
}