package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for existing login.
 *
 */
public class PasswordNotEquals extends ValidationException {

	public PasswordNotEquals(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotEquals(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotEquals(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
