package controller.command.impl;

import bean.*;
import controller.command.Command;
import service.LibraryService;
import service.factory.ServiceFactory;
import service.exeption.ServiceException;

/**
 * The type Edited edition.
 */
public class EditedEdition implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            String id = request.substring(request.indexOf(paramDelimiter) + 1, request.indexOf(paramDelimiter, request.indexOf(paramDelimiter) + 1));

            Edition edition = EditionCreator.valueOf(request.substring(request.indexOf(paramDelimiter, request.indexOf(paramDelimiter) + 1) + 1));

            ServiceFactory factory = ServiceFactory.getInstance();
            LibraryService service = factory.getLibraryService();

            service.editedEdition(id, edition);
            response = "Edited completed";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            response = "Error during Edited procedure";
        } catch (ServiceException e) {
            response = "Error during Edited procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}