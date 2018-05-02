/**
 * The FullCarException is thrown in the case that cargo is added to a full Car object.
 */
public class FullCarException extends Exception{
    /**
     * Constructs a new FullCarExceptions with an empty String as its message.
     */
    public FullCarException() {
        super("");
    }

    /**
     * Constructs a new FullCarExceptions with an empty String as its message.
     * @param message the detail message.
     */
    public FullCarException(String message) {
        super(message);
    }
}
