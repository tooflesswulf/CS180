import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project1- TextFilter
 *
 * This project has four parts. Censoring, replacing, censoring personal information, and looping the program.
 *
 * Part 1: Censoring words.
 * The program takes in a passage and a target string. It will the print the same passage back, but with all
 *  occurrences of the target string replaced with X.
 *
 * Part 2: Replacing words.
 * The program takes in a passage, a target string, and a replacement string. It will print the same passage
 *  back, but with all occurrences of the target string replaced with the replacement string.
 *
 * Part 3: Censoring personal information.
 * The program takes in a structured passage with the form `Type: Information`.
 *  Name: censor all but the first and last characters
 *  Email: censor [a-z]******@[a-z]****.edu
 *  Phone: censor all the numbers except the last 4 numbers.
 *
 * @author Albert Xu, xu1018
 *
 * @version 2/6/18
 *
 */

public class TextFilter {
    
    /**
     * Finds `find` in the passage `in` and replace all occurrences with `replace`
     * @param in the passage to run the filter on.
     * @param find the string to find.
     * @param replace the string to replace the pattern with.
     * @return the modified passage with `find` replaced by `replace`.
     */
    private static String findReplace(String in, String find, String replace) {
        String pattern = "(?<![a-z])(" + find + ")(?![a-z])";
        return regexReplaceWrapper(in, pattern, replace);
    }
    
    private static String regexReplaceWrapper(String str, String pattern, String replace) {
        StringBuffer sb = new StringBuffer();
        
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        
        while(m.find()) {
            m.appendReplacement(sb, replace);
        }
        m.appendTail(sb);
        
        return sb.toString();
    }
    
    private static String censorLine(String in) {
        String arr[] = in.split(": ");
        
        String newString = "[REDACTED]";
        String pattern;
        
        if(arr.length < 2) {
            return in;
        }
        String info = arr[1];
        switch(arr[0]) {
            case "Name":
                pattern = "(?<!^)(\\w)(?!$)";
                newString = regexReplaceWrapper(info, pattern, "*");
                break;
            case "Email":
                int lastDot = info.lastIndexOf('.');
                if(lastDot < 0) {
//                    System.out.println("The given email is improperly formatted: " + info);
                    return in;
                }
                String extension = info.substring(lastDot);
                String theRest = info.substring(0, lastDot);
                
                pattern = "(?<!^|@)(\\w)";
                newString = regexReplaceWrapper(theRest, pattern, "*") + extension;
                break;
            case "Phone":
                pattern = "[0-9](?![0-9]{0,3}$)";
                newString = regexReplaceWrapper(info, pattern, "*");
                break;
            default:
                return in;
        }
        
        return arr[0]+": "+newString;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Print hello message
        System.out.println("Welcome to TextFilter!");
        
        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering = true;
        
        while(keepFiltering) {
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: \n"+
                    "1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information\n\n\n");
            
            // Save their choice
            String choice=sc.nextLine();
            
            if (choice.equals("1")) {
                // Censoring words.
                System.out.println("Please enter the passage you would like filtered: ");
                String passage = sc.nextLine();  // The text to be filtered
                
                System.out.println("Please enter the word you would like to censor: ");
                String word = sc.nextLine();  // The word to be censored from the text phrase
                
                System.out.println("Uncensored: ");
                System.out.println(passage);
                
                //Create an appropriate censor string.
                char[] letters = new char[word.length()];
                Arrays.fill(letters, 'X');
                String newPassage = findReplace(passage, word, String.valueOf(letters));
                
                System.out.println("Censored: ");
                System.out.println(newPassage);
                
            } else if (choice.equals("2")) {
                // Replacing Words
                System.out.println("Please enter the passage you would like filtered: ");
                String passage = sc.nextLine();  // The text to be filtered
                
                System.out.println("Please enter the word you would like to replace: ");
                String word = sc.nextLine();  // The word to be replaced from the text phrase
                
                System.out.println("Please enter word you would like to insert: ");
                String replace = sc.nextLine();  // The word to be inserted in the text phrase
                
                System.out.println("Uncensored: ");
                System.out.println(passage);
                
                String newPassage = findReplace(passage, word, replace);
                
                System.out.println("Censored: ");
                System.out.println(newPassage);
                
            } else if (choice.equals("3")) {
                // PART 3 - Censoring Personal Information
                
                /*
                 * DO NOT ALTER THIS CODE! This formatting is imperative to the completion of this task.
                 *
                 * Keep asking for input until the user enters an empty line
                 * If they enter an empty line and the phrase is empty, keep waiting for input
                 */
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                
                System.out.println("Please enter the phrase you would like to censor information from: ");
                
                while (true) {
                    // Obtain a line from the user
                    String temp = sc.nextLine();
                    
                    if (sb1.length()!=0 && temp.isEmpty()) {
                        break;
                    } else if (sb1.length()==0 && temp.isEmpty()) {
                        continue;
                    }
                    
                    // Add the contents of temp into the phrase
                    sb1.append(temp);
                    sb1.append('\n');
    
                    sb2.append(censorLine(temp));
                    sb2.append('\n');
                }
                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(sb1.toString());
                
                // Print the censored passage
                System.out.println("Censored: ");
                System.out.println(sb2.toString());
                
            } else {
                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");
            }
            
            keepFiltering = getContinueResp(sc);
        }
        sc.close();
        System.out.println("Thank you for using TextFilter!\n");
    }
    
    /**
     * Prompts the user for `yes` and returns true if `yes`, false otherwise.
     * @param sc the scanner that we read from
     * @return true or false, corresponding to the user's input.
     */
    private static boolean getContinueResp(Scanner sc) {
        System.out.println("Would you like to keep filtering? Yes/No");
        String in = sc.nextLine().toLowerCase().trim();
        while(in.equals("")) {
            in = sc.nextLine().toLowerCase().trim();
        }
        switch (in) {
            case "yes": case "y":
                return true;
            case "no": case "n":
                return false;
            default:
                return false;
        }
    }
}
