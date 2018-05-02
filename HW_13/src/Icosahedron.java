/**
 * Icosahedron
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public class Icosahedron extends Polyhedron {
    public Icosahedron(double sideLength) {
        super(sideLength);
    }
    
    @Override
    public double getSurfaceArea() {
        return 5*Math.sqrt(3) * getSideLength()*getSideLength();
    }
    
    @Override
    public double getVolume() {
        return Math.pow(getSideLength(), 3) *((15.0 + 5*Math.sqrt(5))/12.0);
    }
    
    @Override
    public String toString() {
        return String.format("Icosahedron[%f]", getSideLength());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Icosahedron) {
            Icosahedron tetr=(Icosahedron) obj;
            return getSideLength() == tetr.getSideLength();
        }
        return false;
    }
}
