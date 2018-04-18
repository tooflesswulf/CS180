import javax.xml.crypto.Data;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class DataInput {
    public static Star[] stars;
    public void read(String filename){
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(filename)));
            ArrayList<Star> stars = new ArrayList<Star>();
            
            bfr.readLine();
            while(true) {
                String s = bfr.readLine();
                if (s==null){
                    break;
                }
                stars.add(parse(s));
            }
            this.stars = stars.toArray(new Star[1]);
            bfr.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
    
    public Star parse(String input){
        String [] initial = input.split(",");
        boolean planets = false;
        if (initial[3].equals("Yes")){
            planets = true;
        }
        return new Star(initial[0],Double.parseDouble(initial[1]),Double.parseDouble(initial[2]),planets);
    }
    
    public void printInfo(StellarGrouping sg, Month month){
        //TODO: Print the requested information
    
        System.out.printf("Grouping name: %s\n", sg.getName());
        System.out.printf("The brightest star in this grouping is %s\n", sg.brightestStar().getName());
        
        for(Star s : sg.getStars()) {
            System.out.printf("%s is %f trillion miles from earth.\n", s.getName(), sg.distanceFromEarth(s));
        }
    
        System.out.printf("The furthest star from earth in this grouping is %f trillion miles from earth.\n", sg.furthestStar());
        System.out.printf("Currently visible to the naked eye: %s\n", sg.isVisible(month) ? "Yes" : "No");
        System.out.printf("Planets orbiting one or more stars in this grouping: %s\n", sg.planetsOrbiting());
        
        //Tip: The numbers printed will only be tested for up to the first three decimal places
    }
    
    public static void main(String[] args) {
        //TODO: Parse command line arguments (the first should be the CSV name, the second should be the month)
        //Tip: You can use <Enum_Name>.valueOf(<String>) to get an enum constant of the same name as the String (case sensitive)
        if(args.length != 2) {
            System.out.println("Bad input!");
            return;
        }
        
        String filename = args[0];
        String month_s = args[1].toUpperCase();
        Month month = Month.valueOf(month_s);
    
        System.out.println(filename);
        
        //TODO: Create an instance of DataInput and pass the filename to .read
        DataInput di = new DataInput();
        di.read(filename);
        
        //TODO: Create a StellarGrouping instance and print the info for that grouping and the month given above
        StellarGrouping canisMajor = new SouthernSummerConstellation("Canis Major", stars);
        di.printInfo(canisMajor, month);
    }
    
}
