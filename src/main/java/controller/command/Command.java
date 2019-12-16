package controller.command;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * The constant paramDelimiter.
     */
    char paramDelimiter = ' ';

    /**
     * Execute string.
     *
     * @param request the request
     * @return the string
     */
    String execute(String request);

}