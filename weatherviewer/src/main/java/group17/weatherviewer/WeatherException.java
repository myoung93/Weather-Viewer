package group17.weatherviewer;

/**
 * Personalized Exception for handling locations
 */
public class WeatherException extends Exception {

    private String message;
    private Throwable cause;

    public WeatherException(String msg) {
        super(msg);
        this.message = msg;
    }

    public WeatherException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public WeatherException(String msg, Throwable cause) {
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
