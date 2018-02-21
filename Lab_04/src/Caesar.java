import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Caesar {
    private static int getSelection(Scanner sc) {
        String resp = sc.nextLine().toLowerCase().trim();
        switch(resp) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                System.out.println("Please enter a 1, 2, or 3.");
                return getSelection(sc);
        }
    }

    private static boolean getContinue(Scanner sc) {
        System.out.println(" Do you want to try decrypting with another key? (y/n)");
        String resp = sc.nextLine().toLowerCase().trim();
        switch(resp) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                return false;
        }
    }

    private static String applyCaesar(String in, int shift) {
        Pattern p = Pattern.compile("([a-z])");
        Matcher m = p.matcher(in);

        StringBuffer sb = new StringBuffer();
        while(m.find()) {
            char chr = m.group().charAt(0);
            int whichLetter = chr-'a' + shift;
            int nLetters = 'z'-'a'+1;

            int shiftCorrection = (whichLetter%nLetters + nLetters) % nLetters; //To correct for negative shifts

            chr = (char)('a' + shiftCorrection);
            m.appendReplacement(sb, Character.toString(chr));
        }
        m.appendTail(sb);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while(true) {
            System.out.print("Welcome to the Caesar cipher encryption/decryption service.\n" +
                    "Select what service you would like below\n" +
                    "1 - Encrypt a message\n" +
                    "2 - Decrypt a message\n" +
                    "3 - Exit\n");
            int selection = getSelection(sc);

            if (selection == 1) {
                System.out.println("Enter the message you would like to encrypt:");
                String in = sc.nextLine().toLowerCase();
                int cipherKey = rand.nextInt(25)+1;
                System.out.println("Encrypted message is: " + applyCaesar(in, cipherKey));
            } else if (selection == 2) {
                System.out.println("Enter the message you would like to decrypt:");
                String in = sc.nextLine().toLowerCase();
                boolean keepGuessing = true;
                while (keepGuessing) {
                    System.out.println("Please enter you guess for a key:");
                    int key = sc.nextInt();
                    sc.nextLine();
                    while (key % 26 == 0) {
                        System.out.println("That's not a valid key.\n Please enter a new guess between 1 and 25 (inclusive)");
                        key = sc.nextInt();
                        sc.nextLine();
                    }
                    System.out.println("The decrypted message with this key is: " + applyCaesar(in, -key));
                    keepGuessing = getContinue(sc);
                }
            } else if(selection==3) {
                break;
            } else {
                System.out.println("The program should not have gotten here.");
                System.out.printf("Selection: %d\n", selection);
            }
        }
        sc.close();

        String.format()
    }
}
