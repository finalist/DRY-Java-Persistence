package nl.finalist.dry.persistence;

/**
 * Should be thrown when a entity is not found.
 * This exception is typically thrown by getters and not by finders.
 * Finders should
 * 
 * @author ernst-jan
 *
 */
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    
    
}