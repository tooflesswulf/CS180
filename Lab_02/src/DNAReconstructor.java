import java.util.Scanner;

/**
 * Lab 02: DNA Analyzer
 *
 * This program will ask the user for two DNA sequences and join the two over the longest overlap.
 *
 * @author xu1018
 *
 * @version 24 Jan 2018
 *
 */

public class DNAReconstructor {

    /**
     * Finds the longest overlap between the end of a and the start of b. To be used for
     *  combining a & b s.t. the overlap is removed.
     * @param a Start string
     * @param b End string
     * @return The index at which b starts being different.
     */
    private static int longestOverlap(String a, String b) {
        int la = a.length();
        int lb = b.length();

        for(int i=Math.min(la, lb); i>0; --i) {
            if(a.substring(la-i).equals(b.substring(0, i)))
                return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input DNA fragments one line at a time.\nWe start with no DNA:\n\n" +
                "Your DNA (1): \"\"\nInitial Sequence: ");

        String init_seq = sc.nextLine().toLowerCase();

        System.out.printf("Your DNA (2): \"%s\"\nSequencer: ", init_seq);

        String sec_seq = sc.nextLine().toLowerCase();
        sc.close();


//        int index = longestOverlap(init_seq, sec_seq);
        int index = 3;
        System.out.println("New Sequence: \""+init_seq + sec_seq.substring(index)+"\"");
    }
}
