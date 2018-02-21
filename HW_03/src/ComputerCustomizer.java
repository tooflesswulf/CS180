import java.util.Scanner;

/**
 * ComputerCustomizer
 *
 * Prompts the user for an input and outputs a price that shows that the inputs were read correctly.
 *
 * @author Albert Xu
 *
 * @version 1/30/18
 *
 */

public class ComputerCustomizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double price = 4999.00;
        double[] processors = {0, 800, 1600, 2400};
        double[] memory = {0, 800, 2400};
        double[] storage = {0, 800, 2800};
        double[] graphics = {0, 600};
        double[] mouse = {0, 50, 149};
    
        //I am aware of using switch/case statements:
        //switch(choice):
        //  case 1: price += 0;
        //  etc.
        //But using arrays is much easier and faster.
        
        System.out.print("CS18000's Computer Customizer\n\n" +
                "Starting price: $4999.00\n\n" +
                "Processor choices\n" +
                "(1) 8-core Intel Xeon W (+ $0.00)\n" +
                "(2) 10-core Intel Xeon W (+ $800.00)\n" +
                "(3) 14-core Intel Xeon W (+ $1600.00)\n" +
                "(4) 18-core Intel Xeon W (+ $2400.00)\n" +
                "Your choice: ");
        int choice = sc.nextInt(); sc.nextLine();
        price += processors[choice-1];
        
        System.out.print("\nMemory choices\n" +
                "(1) 32GB DDR4 RAM (+ $0.00)\n" +
                "(2) 64GB DDR4 RAM (+ $800.00)\n" +
                "(3) 128GB DDR4 RAM (+ $2400.00)\n" +
                "Your choice: ");
        choice = sc.nextInt(); sc.nextLine();
        price += memory[choice-1];
        
        System.out.print("\nStorage choices\n" +
                "(1) 1TB SSD (+$0.00)\n" +
                "(2) 2TB SSD (+$800.00)\n" +
                "(3) 4TB SSD (+$2800.00)\n" +
                "Your choice: ");
        choice = sc.nextInt(); sc.nextLine();
        price += storage[choice-1];
        
        System.out.print("\nGraphics choices\n" +
                "(1) Radeon Pro Vega 56 (+ $0.00)\n" +
                "(2) Radeon Pro Vega 64 (+ $600.00)\n" +
                "Your choice: ");
        choice = sc.nextInt(); sc.nextLine();
        price += graphics[choice-1];
        
        System.out.print("\nMouse or Trackpad\n" +
                "(1) Mouse (+ $0.00)\n" +
                "(2) Trackpad (+ $50.00)\n" +
                "(3) Both (+ $149.00)\n" +
                "Your choice: ");
        choice = sc.nextInt(); sc.nextLine();
        price += mouse[choice-1];
        
        System.out.printf("\nTotal price: $%.2f", price);
    }
}