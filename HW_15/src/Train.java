/**
 * The Train class represents a linked list of Car objects.
 */
public class Train {
    /**
     * The size of Cars that are newly created as a result of adding elements to the train or appendCar(T cargo).
     */
    private int newCarSize=1;

    /**
     * Points to the first Car in the Train or null if the Train has no Cars.
     */
    private Car engine=null;

    /**
     * Points to the last Car in the Train or null if the Train has no Cars.
     */
    private Car caboose=null;

    /**
     * Creates a Train with no cars. By default, the newCarSize will be set to 1.
     */
    public Train() {
    }

    /**
     * Creates a Train with the passed cars in the passed array. The Cars should be ordered according to the order by
     * which they are presented in the array, with the Car at index 0 being first. Any null values in the cars array
     * should be ignored. By default, the newCarSize will be set to 1.
     *
     * @param cars The cars to be linked together as part of this train
     */
    public Train(Car[] cars) {
        if(cars == null || cars.length == 0) {
            return;
        }
        engine = caboose = cars[0];
        linkCarHelper(cars, 1);
    }
    
    private void linkCarHelper(Car[] cars, int i) {
        if(i >= cars.length) return;
        caboose.setNextCar(cars[i]);
        caboose = cars[i];
        linkCarHelper(cars, i+1);
    }

    /**
     * Returns the size of Cars that are newly created as a result of adding elements to the train or appendCar(T cargo).
     * @return the size of Cars that are newly created as a result of adding elements to the train or appendCar(T cargo).
     */
    public int getNewCarSize() {
        return newCarSize;
    }

    /**
     * Sets the new Car size f.or this Train. This size will constrain only Cars that are newly created as a result
     * of adding elements to the train or appendCar(T cargo).
     */
    public void setNewCarSize(int newCarSize) {
        this.newCarSize = newCarSize;
    }

    /**
     * Returns the first Car in the Train or null if the Train has no Cars.
     * @return the first Car in the Train or null if the Train has no Cars.
     */
    public Car getEngine() {
        return engine;
    }

    /**
     * Returns the last Car in the Train or null if the Train has no Cars.
     * @return the last Car in the Train or null if the Train has no Cars.
     */
    public Car getCaboose() {
        return caboose;
    }

    /**
     * Returns the number of Cars on this Train.
     * @return the number of Cars on this Train.
     */
    public int size() {
        return sizeHelper(engine);
    }
    
    private int sizeHelper(Car head) {
        if(head == null) return 0;
        return sizeHelper(head.getNextCar())+1;
    }

    /**
     * Returns a reference to the Car at the specified position on this Train.
     * @param carIndex index of the Car to return
     * @return the car at the specified position on the Train.
     * @throws IndexOutOfBoundsException if the index is out of range (zero-based)
     */
    public Car get(int carIndex) throws IndexOutOfBoundsException {
        return getHelper(carIndex, engine);
    }
    
    private Car getHelper(int i, Car head) throws IndexOutOfBoundsException {
        if(head==null) throw new IndexOutOfBoundsException();
        if(i==0) return head;
        
        return getHelper(i-1, head.getNextCar());
    }

    /**
     * Returns a reference to the cargo in the Car at the specified position on this Train.
     * @param carIndex index of the Car to search
     * @param cargoIndex index of the cargo in the Car to return
     * @return the cargo in the Car at the specified position on the Train.
     * @throws IndexOutOfBoundsException if the index is out of range f.or either the Train or the Car (zero-based)
     */
    public Object get(int carIndex, int cargoIndex) throws IndexOutOfBoundsException {
        return get(carIndex).get(cargoIndex);
    }

    /**
     * Connects the passed Car to the end of the Train and makes it the new caboose. If the car parameter is already
     * linked to another Car object, it should be linked to null after being appended to the end of the Train.
     * @param car Car to append to the end of the Train.
     */
    public void appendCar(Car car) {
        if(caboose == null) {
            engine = car;
            caboose = car;
            return;
        }
        caboose.setNextCar(car);
        caboose = car;
    }

    /**
     * Connects a new, empty Car to the end of the Train. The cargo argument can be added to the Car or ignored.
     * @param cargo A sample of cargo the new Car will be carrying.
     */
    public void appendCar(Object cargo) {
        Car toAdd = new Car(getNewCarSize());
        toAdd.set(0, cargo);
        appendCar(toAdd);
    }

    /**
     * Replaces the cargo in the Car at the specified position on this Train.
     * @param carIndex index of the Car to search
     * @param cargoIndex index of the cargo in the Car to replace
     * @param cargo element to replace the cargo at the given location
     * @throws IndexOutOfBoundsException if the index is out of range f.or either the Train or the Car (zero-based)
     */
    public void set(int carIndex, int cargoIndex, Object cargo) throws IndexOutOfBoundsException {
        get(carIndex).set(cargoIndex, cargo);
    }

    /**
     * Places additional object onto the first Car in the Train that has space available
     * If such a Car does not exist on this train, a new Car is added to the end of the
     * train. Cargo should be placed in the first available position on the selected Car.
     * @param cargo Additional cargo to place on the Train
     */
    public void addCargo(Object cargo) {
        addCargoHelper(cargo, engine);
    }
    
    private void addCargoHelper(Object c, Car head) {
        try {
            head.addCargo(c);
        } catch(FullCarException e) {
            addCargoHelper(c, head.getNextCar());
        } catch(NullPointerException e) {
            appendCar(c);
        }
    }

    /**
     * Empties each Car of the Train. The Cars should not be removed from the Train.
     */
    public void emptyTrainCars() {
        emptyTrainCarsHelper(engine, 0);
    }
    
    private void emptyTrainCarsHelper(Car head, int cargoIndex) {
        if(head == null) return;
        if(cargoIndex >= head.getCargo().length) {
            emptyTrainCarsHelper(head.getNextCar(), 0);
        } else {
            head.getCargo()[cargoIndex] = null;
            emptyTrainCarsHelper(head, cargoIndex+1);
        }
    }

    /**
     * Train says (prints on a new line) "Toot toot!"
     */
    public void boilerUp() {
        System.out.println("Toot toot!");
    }
    
    public static void main(String[] args) {
        Train t = new Train();
        t.setNewCarSize(2);
        
        t.addCargo("Hello :D");
        t.addCargo("Hello2 :D");
    
        System.out.println(t.getEngine().get(0));
        System.out.println(t.getEngine().get(1));
    
        t.appendCar("msg2");
        t.appendCar("msg2");
        t.appendCar("msg2");
        t.appendCar("msg2");
        t.appendCar("msg2");
        t.appendCar("msg2");
        t.appendCar("msg2");
        System.out.println(t.get(1).get(0));
        System.out.println(t.get(2).get(0));
        System.out.println(t.size());
        System.out.println(t.get(t.size()-1));
        System.out.println(t.getCaboose());
        
        t.boilerUp();
    }
}

