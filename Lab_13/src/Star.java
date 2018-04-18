/**
 * Star
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public class Star {
    private String name;
    private double magnitude;
    private double lightyearsFromEarth;
    private boolean planetsOrbiting;
    
    public Star(String name, double magnitude, double lightyearsFromEarth, boolean planetsOrbiting) {
        this.name = name;
        this.magnitude = magnitude;
        this.lightyearsFromEarth = lightyearsFromEarth;
        this.planetsOrbiting = planetsOrbiting;
    }
    
    public String getName() { return name; }
    public double getMagnitude() { return magnitude; }
    public double getLightyearsFromEarth() { return lightyearsFromEarth; }
    public boolean getPlanetsOrbiting() { return planetsOrbiting; }
    
    public void setName(String name) { this.name=name; }
    public void setMagnitude(double magnitude) { this.magnitude=magnitude; }
    public void setLightyearsFromEarth(double lightyearsFromEarth) { this.lightyearsFromEarth=lightyearsFromEarth; }
    public void setPlanetsOrbiting(boolean planetsOrbiting) { this.planetsOrbiting=planetsOrbiting; }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Star) {
            Star other = (Star) obj;
            
            return (name==null ? other.name==null : other.name.equals(name)) &&
                    other.magnitude==magnitude &&
                    other.lightyearsFromEarth==lightyearsFromEarth &&
                    other.planetsOrbiting==planetsOrbiting;
        }
        
        return super.equals(obj);
    }
}