package controller;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command provider.
 */
final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    /**
     * Instantiates a new Command provider.
     */
    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.ADD_NEW_EDITION, new AddNewEdition());
        repository.put(CommandName.MIGRATE, new Migrate());
        repository.put(CommandName.EDITED_EDITION, new EditedEdition());
        repository.put(CommandName.DELETE_EDITION_TYPE, new DeleteEditionType());
        repository.put(CommandName.SHOW_EDITIONS, new ShowEditions());
        repository.put(CommandName.SORT_EDITIONS, new SortEditions());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * Gets command.
     *
     * @param name the name
     * @return the command
     */
    Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }

}