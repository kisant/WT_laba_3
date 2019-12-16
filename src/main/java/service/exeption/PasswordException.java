package service.exeption;

/**
 * The type Password exception.
 */
public class PasswordException extends ServiceException {

    /**
     * Instantiates a new Password exception.
     */
    public PasswordException() {
        super();
    }

    /**
     * Instantiates a new Password exception.
     *
     * @param message the message
     */
    public PasswordException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Password exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Password exception.
     *
     * @param cause the cause
     */
    public PasswordException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Password exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}