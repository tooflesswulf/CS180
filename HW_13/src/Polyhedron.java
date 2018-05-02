/**
 * Polyhedron
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/18/18
 *
 */

public abstract class Polyhedron {
    private double sideLength;
    
    public Polyhedron(double sideLength) {
        setSideLength(sideLength);
    }
    
    public double getSideLength() {
        return sideLength;
    }
    
    public void setSideLength(double sideLength) {
        if(sideLength < 0) {
            throw new IllegalArgumentException("sideLength cannot be negative: " + sideLength);
        }
        this.sideLength=sideLength;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Polyhedron) {
            Polyhedron poly=(Polyhedron) obj;
            return poly.sideLength == sideLength;
        }
        return super.equals(obj);
    }
    
    @Override
    public String toString() {
        return String.format("Polyhedron[%f]", sideLength);
    }
    
    public abstract double getSurfaceArea();
    public abstract double getVolume();
}