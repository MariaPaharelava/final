package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for existing login.
 */
public class StringTooLongException extends ValidationException {

    public StringTooLongException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public StringTooLongException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    public StringTooLongException(String message, Exception e) {
        super(message, e);
        // TODO Auto-generated constructor stub
    }

}
