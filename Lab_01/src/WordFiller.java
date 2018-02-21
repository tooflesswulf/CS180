import java.util.Arrays;
import java.util.Scanner;

/**
 * Lab 01: Zany Word Filler Game
 *
 * This program will ask the user for various words (adjectives, nouns, etc.) and plug
 *  those into a template to make a paragraph
 *
 * @author xu1018
 *
 * @version 17 Jan 2018
 *
 */

public class WordFiller {
    public static void main(String[] args) {
        String[] template = {"Pizza was invented by a ","", " ", ""," chef named ","",
                ". To make a pizza, you need to take a lump of ","",", and make a thin, round ",""," ","",
                ". Then you cover it with ",""," sauce, ",""," cheese, and fresh chopped ","",
                ". Next you have to bake it in a very hot ","",". When it is done, cut it into ",""," ","",
                ". Some kids like ",""," pizza the best, but my favorite is the ",""," pizza. I eat ","",
                " whole pizzas every day that I eat pizza! In fact, I eat pizza ",""," days every week! That" +
                " means I eat ",""," pizzas every week!"};

//        String template2 = "Pizza was invented by a %s %s chef named %s. To make a pizza, you need to take a lump of "+
//                "%s, and make a thin, round %s %s. Then you cover it with %s sauce, %s cheese, and fresh chopped %s." +
//                " Next you have to bake it in a very hot %s. When it is done, cut it into %s. Some kids like %s pizza the best, but my favorite is the ",""," pizza. I eat ","",
//                "whole pizzas every day that I eat pizza! In fact, I eat pizza ",""," days every week! That" +
//                "means I eat %s pizzas every week!"

        // descriptors is an array of id's; this was done b/c I'm too lazy to type out the string every time.
        String[] descriptor_map = {"adjective", "nationality", "person", "noun", "plural noun", "number",
        "shapes", "food", "number (read as float)"};
        int[] descriptors = {0,1,2,3,0,3,0,0,4,3,5,6,7,7};

        String[] inputs = new String[descriptors.length];

        // read inputs
        Scanner sc = new Scanner(System.in);
        System.out.println("Please provide the following:");
        for(int i=0; i<descriptors.length; ++i) {
            System.out.println(descriptor_map[descriptors[i]]);

            inputs[i] = sc.nextLine();
        }

        // read the number inputs
        System.out.println("number (read as int)");
        int pizza_per_day = sc.nextInt();
        sc.nextLine();
        System.out.println("number (read as int)");
        int pizza_per_week = sc.nextInt();
        sc.nextLine();

        String out = "";
        int counter=0;
        // messy formatting b/c java unpacking is weird.
        for(String s : template) {
            if(s.equals("")) {
                if(counter < inputs.length)
                    out += inputs[counter];
                else if(counter==inputs.length)
                    out += String.valueOf(pizza_per_day);
                else if(counter==inputs.length+1)
                    out += String.valueOf(pizza_per_week);
                else if(counter==inputs.length+2)
                    out += String.valueOf(pizza_per_day*pizza_per_week);
                counter++;
            } else
                out += s;
        }

        System.out.println(out);
    }
}
