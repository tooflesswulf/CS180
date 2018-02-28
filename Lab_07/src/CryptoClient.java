import java.util.*;
import java.lang.*;

public class CryptoClient {
    private static CryptoCurrency getCurrency(String choice, int invest, Demand[] demands)
    {
        switch(choice)
        {
            case "1":
                return new JVarnCoin(demands[0], invest);
            case "2":
                return new VictoryCoin(demands[1], invest);
            case "3":
                return new XerosCoin(demands[2], invest);
            default:
                return null;
        }
    }
    
    private static void menu(CryptoCurrency cur, int n, Scanner sc) {
        if(cur==null) return;
        System.out.printf("How would you like to acquire capital for coin %d?\n" +
                "\t1. Mine it\n" +
                "\t2. Purchase some with current CryptoCurrency profit\n" +
                "\t3. Do not alter CryptoCurrency\n", n);
        
        String s = sc.nextLine();
        switch (s) {
            case "1":
                System.out.println("How many attempts would you like to make?");
                int att = sc.nextInt(); sc.nextLine();
                cur.mine(att);
                break;
            case "2":
                System.out.println("How many coins would you like to purchase?");
                int ncoin = sc.nextInt(); sc.nextLine();
                cur.purchase(ncoin);
                break;
            case "3":
                break;
            default:
                break;
        }
    }
    
    private static double calcProfit(CryptoCurrency cur) {
        if(cur==null) return Integer.MIN_VALUE;
        
        cur.assessProfit();
        return cur.getProfit();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        //DO NOT EDIT CODE BELOW THIS-----------------------------------------------------------------------------------
        CryptoCurrency s1, s2, s3;
        CryptoClient c = new CryptoClient();
        Scanner s = new Scanner(System.in);
        boolean investAgain = true;
        Demand JVarnDemand = c.Genrandom();
        Demand VictoryDemand = c.Genrandom();
        Demand XerosDemand = c.Genrandom();
        do {
            System.out.printf("Welcome to CryptoCo, the leading exchange for semi-legitimate currency!\n" +
                    "Our cheapest package involves allows you to invest in Three CryptoCurrencies\n" +
                    "Here are your options:\n\t1.JVarnCoin\n\t2.VictoryCoin\n\t3.XerosCoin\n" +
                    "Please enter what your first investment.\n");
            String choice1 = s.nextLine();
            System.out.printf("How many coins will you start with?\n");
            int invest1 = s.nextInt();
            System.out.printf("Great! Now what is the second crypto currency you would like to invest in?\n");
            s.nextLine();
            String choice2 = s.nextLine();
            System.out.printf("How many coins will you start with?\n");
            int invest2 = s.nextInt();
            System.out.printf("Great! Now what is the third crypto currency you would like to invest in?\n");
            s.nextLine();
            String choice3 = s.nextLine();
            System.out.printf("How many coins will you start with?\n");
            int invest3 = s.nextInt();
            s.nextLine();
            System.out.println("Calculating... ");
            //DO NOT EDIT THE CODE ABOVE THIS---------------------------------------------------------------------------
            
            //TODO: Assign coins to s1, s2, and s3 based on the user's selections
            Demand[] demands = {JVarnDemand, VictoryDemand, XerosDemand};
            s1 = getCurrency(choice1, invest1, demands);
            s2 = getCurrency(choice2, invest2, demands);
            s3 = getCurrency(choice3, invest3, demands);
            
            System.out.println("great!");
            
            //TODO: Implement the menu for each of the user's three selections.
            menu(s1, 1, s);
            menu(s2, 2, s);
            menu(s3, 3, s);
            
            System.out.println("Calculating profit:\n1");
            double p1 = calcProfit(s1);
            System.out.println("Done\n2");
            
            double p2 = calcProfit(s2);
            System.out.println("Done\n3");
            
            double p3 = calcProfit(s3);
            System.out.println("Done");
            
            //TODO: Compare results
            double maxprof = p1;
            int maxid = 1;
            if(maxprof < p2) {
                maxprof = p2;
                maxid = 2;
            }
            if(maxprof < p3) {
                maxprof = p3;
                maxid = 3;
            }
            
            System.out.printf("The best option is option %d with CryptoCo account profit of $%.2f.\n" +
                    "Would you like to invest again? (y/n)\n", maxid, maxprof);
            
            String choice = s.nextLine();
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                investAgain = true;
            } else {
                investAgain = false;
            }
            
        } while (investAgain);
    }
    //NOTE: THIS LINE SHOULD NOT BE BELOW LINE 175
    
    public Demand Genrandom() {
        Random r = new Random();
        int chance = r.nextInt(5);
        switch (chance) {
            case 0:
                return Demand.HIGH;
            case 1:
                return Demand.ABOVE_AVERAGE;
            case 2:
                return Demand.AVERAGE;
            case 3:
                return Demand.BELOW_AVERAGE;
            case 4:
                return Demand.LOW;
        }
        return Demand.AVERAGE;
    }
}