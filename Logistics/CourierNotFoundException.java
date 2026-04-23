/**
 * Custom exception class to handle cases where a user tries to 
 * read, update, or delete a Courier ID that does not exist.
 */
public class CourierNotFoundException extends Exception {
    public CourierNotFoundException(String message) {
        super(message);
    }
}