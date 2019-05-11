package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for no existing login.
 *
 */
public class LoginAlreadyNoExistsException extends ValidationException {

	public LoginAlreadyNoExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LoginAlreadyNoExistsException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public LoginAlreadyNoExistsException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
