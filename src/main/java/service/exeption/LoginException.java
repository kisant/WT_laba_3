package service.exeption;

/**
 * The type Login exception.
 */
public class LoginException extends ServiceException {

    /**
     * Instantiates a new Login exception.
     */
    public LoginException() {
        super();
    }

    /**
     * Instantiates a new Login exception.
     *
     * @param message the message
     */
    public LoginException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Login exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Login exception.
     *
     * @param cause the cause
     */
    public LoginException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Login exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}