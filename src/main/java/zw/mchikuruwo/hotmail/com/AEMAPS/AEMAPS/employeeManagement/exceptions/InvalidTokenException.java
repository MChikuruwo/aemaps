package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.exceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }
}