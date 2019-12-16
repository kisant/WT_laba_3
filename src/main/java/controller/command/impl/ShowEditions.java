package controller.command.impl;

import controller.command.Command;
import service.LibraryService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Show editions.
 */
public class ShowEditions implements Command {

    @Override
    public String execute(String request) {
        String response;

        ServiceFactory factory = ServiceFactory.getInstance();
        LibraryService service = factory.getLibraryService();

        try {
            response = service.showEdition();
        } catch (ServiceException e) {
            response = "Error during showing procedure " + e.getMessage();
        }

        return response;
    }

}