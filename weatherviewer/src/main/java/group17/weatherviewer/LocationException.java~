package project;

/**
 * Personalized Exception for handling locations
 */
public class LocationException extends Exception {

    private String message;
    private Throwable cause;

    public LocationException(String msg) {
        super(msg);
        this.message = msg;
    }

    public LocationException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public LocationException(String msg, Throwable cause) {
        super(msg, cause);
        this.message = msg;
        this.cause = cause;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
