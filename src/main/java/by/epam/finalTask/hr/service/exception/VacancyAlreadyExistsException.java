package by.epam.finalTask.hr.service.exception;

/**
 * Validation exception for existing login.
 *
 */
public class VacancyAlreadyExistsException extends ValidationException {

	public VacancyAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public VacancyAlreadyExistsException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public VacancyAlreadyExistsException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
