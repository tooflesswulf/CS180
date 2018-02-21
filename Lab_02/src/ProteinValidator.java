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

public class ProteinValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the DNA fragment to be validated: ");
        String dna = sc.nextLine().toLowerCase();
        sc.close();
        System.out.println("Input DNA: "+dna);

        int ix = dna.indexOf("atg");
        System.out.printf("Start codon position: %d\n", ix);

        String gene = dna.substring(ix);
        System.out.println("Gene: " + gene);

        Boolean end1 = gene.endsWith("tag");
        Boolean end2 = gene.endsWith("taa");
        Boolean end3 = gene.endsWith("tga");

        Boolean isProtein = end1|end2|end3;
        System.out.printf("Protein: %s\n", isProtein?"true":"false");
//        System.out.println("Protein: true");
    }
}
