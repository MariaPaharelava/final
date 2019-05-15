package by.epam.finalTask.hr.service.exception;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;

/**
 * The super class for all exception, arise during validation.
 *
 * @author Ivan Chernikau
 */
public class ValidationException extends DAOException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {
        super(e);
    }

    public ValidationException(String message, Exception e) {
        super(message, e);
    }

}
