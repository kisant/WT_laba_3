package controller.command.impl;

import controller.command.Command;
import service.LibraryService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Sort editions.
 */
public class SortEditions implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            String comparatorName = request.substring(request.indexOf(paramDelimiter) + 1);

            ServiceFactory factory = ServiceFactory.getInstance();
            LibraryService service = factory.getLibraryService();

            response = service.sortEdition(comparatorName);
        } catch (NumberFormatException e) {
            response = "Incorrect comparator name";
        } catch (ServiceException e) {
            response = "Error during sorted procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}