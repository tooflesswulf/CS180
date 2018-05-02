/**
 * The Car class represents a single element of the Train. Each Car contains an array of Objects.
 */
public class Car {
    /**
     * Array of objects stored on this Car
     */
    private Object[] cargo;

    /**
     * Points to the next Car in the Train if it exists, or null otherwise.
     */
    private Car nextCar = null;
    
    /**
     * Creates an empty Car with a cargo array of the passed size.
     * @param size the size of the car's cargo array
     */
    public Car(int size) {
        cargo = new Object[size];
    }

    /**
     * Places additional objects onto this Car. Throws FullCarException if no positions in the Car are null.
     * @param c Additional cargo to place on the Car
     * @throws FullCarException if the Car is full
     */
    public void addCargo(Object c) throws FullCarException{
        if(isFull()) throw new FullCarException();
        addCargoHelper(c, 0);
    }
    
    private void addCargoHelper(Object c, int i) throws FullCarException {
        if(i >= capacity()) throw new FullCarException();
        if(cargo[i] == null) {
            cargo[i] = c;
            return;
        }
        addCargoHelper(c, i+1);
    }

    /**
     * Returns a reference to the array of objects stored on this Car
     * @return a reference to the array of objects stored on this Car
     */
    public Object[] getCargo() {
        return cargo;
    }

    /**
     * Returns the number of elements that can be stored in this Car.
     * @return the number of elements that can be stored in this Car.
     */
    public int capacity() {
        return cargo.length;
    }

    /**
     * Returns the number of elements currently stored in this Car
     * @return the number of elements currently stored in this Car
     */
    public int size() {
        return sizeHelper(0);
    }
    
    private int sizeHelper(int i) {
        if(i >= capacity()) { return 0; }
        if(cargo[i] == null) return 0;
        return sizeHelper(i+1)+1;
    }

    /**
     * Returns the element at the specified location in the Car.
     * @param index the index of the element to be removed.
     * @return the element that was removed from the car.
     * @throws IndexOutOfBoundsException if the index is out of range (zero-based)
     */
    public Object get(int index) throws IndexOutOfBoundsException{
        return cargo[index];
    }

    /**
     * Replaces the cargo in the Car at the specified position on this Train. null is a permitted argument.
     * @param index index of the cargo in the Car to replace
     * @param c     element to replace the cargo at the given location
     * @throws IndexOutOfBoundsException if the index is out of range (zero-based)
     */
    public void set(int index, Object c) throws IndexOutOfBoundsException {
        cargo[index] = c;
    }

    /**
     * Returns true if each position in the cargo array is not null, and returns false otherwise.
     * @return true if each position in the cargo array is not null, and returns false otherwise.
     */
    public boolean isFull() {
        return size() == capacity();
    }

    /**
     * Sets this Car's nextCar field to reference the passed Car
     * @param nextCar Car f.or this Car to reference
     */
    public void setNextCar(Car nextCar){
        this.nextCar = nextCar;
    }

    /**
     * Returns the reference to the nextCar if it exists, or null otherwise
     * @return the reference to the nextCar if it exists, or null otherwise
     */
    public Car getNextCar() {
        return nextCar;
    }
}
