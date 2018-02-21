import java.util.Scanner;

/**
 * SqrtEstimator
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/5/18
 *
 */

public class SqrtEstimator {
    private static final int timeout = 20;
    
    private static double approxSqrt(double start, double end, double n, int precision) {
        double guess = (start + end)/2;

        double error = Math.abs(guess*guess - n);
        double targError = Math.pow(10, -precision);
        while(error > targError) {
            if(guess*guess > n) {
                end = guess;
            } else {
                start = guess;
            }
    
            guess = (start + end)/2;
    
            error = Math.abs(guess*guess - n);
            targError = Math.pow(10, -precision);
        }
        return guess;
        
//
//
//        if(error <= targError) {
//            return guess;
//        }
//
//        if(guess*guess > n) {
//            return approxSqrt(start, guess, n, precision);
//        } else {
//            return approxSqrt(guess, end, n, precision);
//        }
    }
    
    private static int getNumber(Scanner sc) {
        int counter = 0;
        while(counter < timeout) {
            System.out.println("Enter a positive number to find the square root of: [type quit to exit]");
            String n = sc.nextLine();
    
            if(n.toLowerCase().trim().equals("quit")) {
                return -1;
            }
            
            try
            {
//                Double d =Double.parseDouble(n);
                int d = Integer.parseInt(n);
                if(d>0) {
                    return d;
                }
            }
            catch(NumberFormatException e){}
    
            System.out.println("Enter a positive number");
            counter++;
        }
        return -1;
    }
    
    private static int getPrecision(Scanner sc) {
        int counter = 0;
        while(counter < timeout) {
            System.out.println("Enter the precision of the estimator (number of digits after decimal point): ");
            String n = sc.nextLine();
        
            if(n.toLowerCase().trim().equals("quit")) {
                return -1;
            }
        
            try
            {
                int d =Integer.parseInt(n);
                if(d>0) {
                    return d;
                }
            }
            catch(NumberFormatException e){}
        
            System.out.println("Enter a positive integer");
            counter++;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        double sqrt;
        int n = getNumber(sc);
        int precision;
        
        while(n>=0) {
            precision = getPrecision(sc);
            if(precision<0) {
                break;
            }
            
            sqrt = approxSqrt(0, n, n, precision);
            
            String s2 = String.format(".%df", precision);
            System.out.printf("Square Root of %d: %"+s2+"\n", n, sqrt);
            
            n = getNumber(sc);
        }
    }
}