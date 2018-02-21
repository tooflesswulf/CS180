import java.util.Scanner;

/**
 * TenseCorrector
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 1/21/18
 *
 */

public class TenseCorrector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Original Sentence: ");
        String line = sc.nextLine();
        sc.close();
        
        // I understand this function uses regex and know how regex works.
        line = line.replaceAll("is", "was");
        line = line.replaceAll("am", "was");
        line = line.replaceAll("are", "were");
        line = line.replaceAll("do", "did");
        System.out.println("Corrected Sentence: " + line + "(Corrected)");
    }
}