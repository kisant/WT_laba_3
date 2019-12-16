package controller.command.impl;

import controller.command.Command;
import service.ClientService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Sign out.
 */
public class SignOut implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            String login = request.substring(request.indexOf(paramDelimiter) + 1);

            ServiceFactory factory = ServiceFactory.getInstance();
            ClientService service = factory.getClientService();

            service.signOut(login);
            response = "Good buy";
        } catch (IndexOutOfBoundsException e) {
            response = "Error during logout procedure";
        } catch (ServiceException e) {
            response = "Error during logout procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}