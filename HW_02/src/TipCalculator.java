import java.util.Scanner;

/**
 * TipCalculator
 *
 * Prompts the user for a price and tip percentage, and calculates the total amount to be paid.
 *
 * @author Albert Xu
 *
 * @version 1/21/18
 *
 */

public class TipCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Price($): ");
        double price = sc.nextDouble();
        sc.nextLine();
        
        System.out.print("Tip Percentage($): ");
        int percent = sc.nextInt();
        sc.close();
        
        System.out.printf("Price Without Tip($): %.2f\n", price);
        System.out.printf("Tip Amount($): %.2f\n", price * percent/100);
        System.out.printf("Total Amount($): %.2f\n", price * (1+percent/100.));
    }
}