package controller.exception;

/**
 * The type Controller exception.
 */
public class ControllerException extends Exception {

    /**
     * Instantiates a new Controller exception.
     */
    public ControllerException() {
        super();
    }

    /**
     * Instantiates a new Controller exception.
     *
     * @param message the message
     */
    public ControllerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Controller exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Controller exception.
     *
     * @param cause the cause
     */
    public ControllerException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Controller exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}