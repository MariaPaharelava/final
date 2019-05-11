package by.epam.finalTask.hr.dao.connectionpool.exception;


public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
