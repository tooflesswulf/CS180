import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * StellarGrouping
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public abstract class StellarGrouping {
    public static final double LY2TrilMI = 5.878625;
    
    private String name;
    private Star[] Stars;
    
    public StellarGrouping(String name, Star[] Stars) {
        this.name = name;
        this.Stars = Stars;
    }
    
    public String getName() { return name; }
    public Star[] getStars() { return Stars; }
    
    public String planetsOrbiting() {
        return "Unknown";
    }
    
    public Star brightestStar() {
        double lowestMag = Stars[0].getMagnitude();
        int iofLowest = 0;
        
        for(int i=1; i<Stars.length; ++i) {
            double mag = Stars[i].getMagnitude();
            if(mag < lowestMag) {
                lowestMag = mag;
                iofLowest = i;
            }
        }
        
        return Stars[iofLowest];
    }
    
    abstract boolean isVisible(Month month);
    
    public double distanceFromEarth(String starName) {
        for(Star s : Stars) {
            if(s.getName().equals(starName)) {
                return distanceFromEarth(s);
            }
        }
        
        return -1;
    }
    
    public double distanceFromEarth(Star s) {
        if(!Arrays.asList(Stars).contains(s)) {
            return -1;
        }
        
        return s.getLightyearsFromEarth() * LY2TrilMI;
    }
    
    public double furthestStar() {
        OptionalDouble max = Arrays.stream(Stars)
                .mapToDouble(Star::getLightyearsFromEarth)
                .max();
        
        if(max.isPresent()) return max.getAsDouble()*LY2TrilMI;
        return -1;
    }
}