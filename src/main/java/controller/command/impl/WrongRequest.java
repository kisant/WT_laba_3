package controller.command.impl;

import controller.command.Command;

/**
 * The type Wrong request.
 */
public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "Wrong request";
    }

}