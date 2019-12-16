package controller.command.impl;

import controller.command.Command;
import service.LibraryService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Migrate.
 */
public class Migrate implements Command {

    @Override
    public String execute(String request) {
        String response = "Migrate completed";

        ServiceFactory factory = ServiceFactory.getInstance();
        LibraryService service = factory.getLibraryService();

        try {
            service.migrate();
        } catch (ServiceException e) {
            response = "Error during migrate procedure " + e.getMessage();
        }

        return response;
    }

}