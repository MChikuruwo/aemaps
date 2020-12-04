package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.exceptions;

public class EmployeeCodeNotFoundException extends RuntimeException {
    public EmployeeCodeNotFoundException() {
        super();
    }

    public EmployeeCodeNotFoundException(String message) {
        super(message);
    }

    public EmployeeCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeCodeNotFoundException(Throwable cause) {
        super(cause);
    }
}
