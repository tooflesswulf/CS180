import java.util.Scanner;

/**
 * Lab 03- 3 or 4 not 20 Questions
 *
 * Asks the user several questions to try to guess whatever animal the user is thinking of.
 *
 * @author Albert Xu, xu1018
 *
 * @version 31 Jan 2018
 *
 */

public class Questions {
    private static boolean getResp(Scanner sc) {
        String in = sc.nextLine().toLowerCase();
        switch(in) {
            case "yes": case "y":
                return true;
            case "no": case "n":
                return false;
            default:
                System.out.println("Please enter either `yes` or `no`.");
                return getResp(sc);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Does it have fur?");
        if(getResp(sc)) {
            System.out.println("Does it live in trees?");
            if(getResp(sc)) {
                System.out.println("Does it eat leaves?");
                if(getResp(sc)) {
                    System.out.println("Is it's poop green?");
                    if(getResp(sc)) {
                        System.out.println("It is a Koala");
                    } else {
                        System.out.println("It is a Panda");
                    }
                } else {
                    System.out.println("Is it a big cat?");
                    if(getResp(sc)) {
                        System.out.println("It is a Jaguar");
                    } else {
                        System.out.println("It is a Monkey");
                    }
                }
            } else {
                System.out.println("Does it live underground?");
                if(getResp(sc)) {
                    System.out.println("It is a Groundhog");
                } else {
                    System.out.println("It is a Rat");
                }
            }
        } else {
            System.out.println("Does it have scales?");
            if(getResp(sc)) {
                System.out.println("Does it live in water?");
                if(getResp(sc)) {
                    System.out.println("It is a Fish");
                } else {
                    System.out.println("It is a Lizard");
                }
            } else {
                System.out.println("Is it an amphibian?");
                if(getResp(sc)) {
                    System.out.println("It is a Newt");
                } else {
                    System.out.println("Can it fly?");
                    if(getResp(sc)) {
                        System.out.println("It is a Hawk");
                    } else {
                        System.out.println("It is a Penguin");
                    }
                }
            }
        }
    }
}
