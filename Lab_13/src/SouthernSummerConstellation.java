/**
 * SouthernSummerConstellation
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public class SouthernSummerConstellation extends StellarGrouping {
    public SouthernSummerConstellation(String name, Star[] Stars) {
        super(name, Stars);
    }
    
    @Override
    boolean isVisible(Month month) {
        switch (month) {
            case NOVEMBER: case DECEMBER: case JANUARY: case FEBRUARY: case MARCH: case APRIL:
                return true;
            default:
                return false;
        }
    }
    
    public String planetsOrbiting() {
        for(Star s:getStars()) {
            if(s.getPlanetsOrbiting()) return "Yes";
        }
        return "Unknown";
    }
}