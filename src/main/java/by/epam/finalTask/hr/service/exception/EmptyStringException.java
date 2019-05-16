package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for existing login.
 */
public class EmptyStringException extends ValidationException {

    public EmptyStringException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public EmptyStringException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    public EmptyStringException(String message, Exception e) {
        super(message, e);
        // TODO Auto-generated constructor stub
    }

}
