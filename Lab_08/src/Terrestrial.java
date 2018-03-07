/**
 * Terrestrial
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/7/18
 *
 */

public class Terrestrial extends Planet{
    private String habitability;
    
    public Terrestrial(double mass, double radius, String name, double distFromSun, String habitability) {
        super(mass, radius, name, distFromSun);
        this.habitability = habitability;
    }
    
    public String getHabitability() {
        return habitability;
    }
    
    public void printInfo() {
        super.printInfo();
        System.out.println("This terrestrial planet is " + habitability);
    }
}