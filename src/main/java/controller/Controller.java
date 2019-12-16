package controller;

import controller.command.Command;

/**
 * The type Controller.
 */
public final class Controller {

    private static final Controller instance = new Controller();
    private final CommandProvider provider = new CommandProvider();

    private final char paramDelimiter = ' ';

    private Controller() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Controller getInstance() {
        return instance;
    }

    /**
     * Execute command string.
     *
     * @param request the request
     * @return the string
     */
    public String executeCommand(String request) {
        Command executionCommand;

        String commandName = "";
        if (request.indexOf(paramDelimiter) != -1) {
            commandName = request.substring(0, request.indexOf(paramDelimiter));
        }
        executionCommand = provider.getCommand(commandName);

        return executionCommand.execute(request);
    }

}