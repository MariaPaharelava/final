package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for existing login.
 */
public class LoginAlreadyExistsException extends ValidationException {

    public LoginAlreadyExistsException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public LoginAlreadyExistsException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    public LoginAlreadyExistsException(String message, Exception e) {
        super(message, e);
        // TODO Auto-generated constructor stub
    }

}
