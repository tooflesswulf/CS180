/**
 * Planet
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/7/18
 *
 */

public class Planet {
    private double mass;
    private double radius;
    private String name;
    private double distFromSun;
    
    public Planet(double mass, double radius, String name, double distFromSun) throws PlutoNotAPlanetException {
        if(name.equalsIgnoreCase("pluto")) {
            throw new PlutoNotAPlanetException("bye pluto");
        }
        
        this.mass = mass;
        this.radius = radius;
        this.name = name;
        this.distFromSun = distFromSun;
    }
    
    public double getVolume() {
        // 4/3 pi r^3
        double r_cubed = radius*radius*radius;
        return 4.0/3 * Math.PI * r_cubed;
    }
    
    public double getDensity() {
        return mass/getVolume();
    }
    
    public double getDistFromSun() {
        return distFromSun;
    }
    
    public double distToPlanet(Planet other) {
        return Math.abs(distFromSun - other.distFromSun);
    }
    
    public String getName() {
        return name;
    }
    
    public void printInfo() {
        System.out.printf("This is the planet %s. It is %.2f AU from the Sun. It has density of %.2f kg/m^3.\n",
                name, distFromSun, getDensity());
    }
}