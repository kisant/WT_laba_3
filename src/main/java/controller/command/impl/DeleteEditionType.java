package controller.command.impl;

import bean.enums.EditionType;
import controller.command.Command;
import service.LibraryService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Delete edition type.
 */
public class DeleteEditionType implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            int indexStart = request.indexOf(Command.paramDelimiter) + 1;
            int indexEnd = request.indexOf(Command.paramDelimiter, indexStart);
            EditionType type = EditionType.valueOf(request.substring(indexStart, indexEnd).toUpperCase());

            indexStart = indexEnd + 1;
            String id = request.substring(indexStart);

            ServiceFactory factory = ServiceFactory.getInstance();
            LibraryService service = factory.getLibraryService();

            service.deleteEdition(id, type);
            response = "Deleted completed";
        } catch (ServiceException e) {
            response = "Error during deleted procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}