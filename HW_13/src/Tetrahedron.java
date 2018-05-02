/**
 * Tetrahedron
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public class Tetrahedron extends Polyhedron{
    public Tetrahedron(double sideLength) {
        super(sideLength);
    }
    
    @Override
    public double getSurfaceArea() {
        return Math.sqrt(3) * getSideLength()*getSideLength();
    }
    
    @Override
    public double getVolume() {
        return Math.pow(getSideLength(), 3) / (6 * Math.sqrt(2));
    }
    
    @Override
    public String toString() {
        return String.format("Tetrahedron[%f]", getSideLength());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tetrahedron) {
            Tetrahedron tetr=(Tetrahedron) obj;
            return getSideLength() == tetr.getSideLength();
        }
        return false;
    }
}