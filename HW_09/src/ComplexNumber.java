/**
 * ComplexNumber
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/22/18
 *
 */

public class ComplexNumber implements Comparable<ComplexNumber> {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber() {
        this(0, 0);
    }
    
    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart=realPart;
        this.imaginaryPart=imaginaryPart;
    }

    public ComplexNumber(ComplexNumber other) throws IllegalArgumentException {
        if(other==null) throw new IllegalArgumentException();
        
        this.realPart= other.getRealPart();
        this.imaginaryPart= other.getImaginaryPart();
    }
    
    public synchronized double getRealPart() { return realPart; }
    public synchronized double getImaginaryPart() { return imaginaryPart; }
    
    public synchronized void setRealPart(double re) { this.realPart= re; }
    public synchronized void setImaginaryPart(double im) { this.imaginaryPart= im; }
    
    public synchronized ComplexNumber conjugate() {
        return new ComplexNumber(realPart, -imaginaryPart);
    }
    
    public synchronized ComplexNumber reciprocal() {
        double mag = realPart*realPart + imaginaryPart*imaginaryPart;

        return new ComplexNumber(realPart/mag, -imaginaryPart/mag);
    }
    
    public synchronized ComplexNumber add(ComplexNumber other) throws IllegalArgumentException {
        if(other==null) throw new IllegalArgumentException();
        return new ComplexNumber(realPart +other.getRealPart(), imaginaryPart +other.getImaginaryPart());
    }
    
    public synchronized ComplexNumber subtract(ComplexNumber other) throws IllegalArgumentException {
        if(other==null) throw new IllegalArgumentException();
        return new ComplexNumber(realPart -other.getRealPart(), imaginaryPart -other.getImaginaryPart());
    }
    
    public synchronized ComplexNumber multiply(ComplexNumber other) throws IllegalArgumentException {
        if(other==null) throw new IllegalArgumentException();
        return new ComplexNumber(realPart *other.getRealPart() - imaginaryPart *other.getImaginaryPart(),
                realPart *other.getImaginaryPart() + imaginaryPart *other.getRealPart());
    }
    
    public synchronized ComplexNumber divide(ComplexNumber other) throws IllegalArgumentException {
        if(other==null) throw new IllegalArgumentException();
        return multiply(other.reciprocal());
    }
    
    public synchronized int compareTo(ComplexNumber other) {
        if(other==null) return -1;
        
        double ore = other.getRealPart();
        double oim = other.getImaginaryPart();
        
        if(ore != realPart) {
            return realPart >ore ? 1 : -1;
        }
        if(oim != imaginaryPart) {
            return imaginaryPart >oim ? 1 : -1;
        }
        return 0;
    }
    
    public synchronized boolean equals(Object other) {
        if(!(other instanceof ComplexNumber)) {
            return false;
        }
        ComplexNumber o = (ComplexNumber) other;
        
        return realPart ==o.getRealPart() && imaginaryPart ==o.getImaginaryPart();
    }
    
    public synchronized String toString() {
        if(imaginaryPart >= 0) {
            return String.format("%f + %fi", realPart, imaginaryPart);
        } else {
            return String.format("%f - %fi", realPart, -imaginaryPart);
        }
    }
}