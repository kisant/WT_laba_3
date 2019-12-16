package controller.command.impl;

import controller.command.Command;
import service.ClientService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Sign in.
 */
public class SignIn implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            String login = request.substring(request.indexOf(paramDelimiter) + 1, request.lastIndexOf(paramDelimiter));
            String password = request.substring(request.lastIndexOf(paramDelimiter) + 1);

            ServiceFactory factory = ServiceFactory.getInstance();
            ClientService service = factory.getClientService();

            service.signIn(login, password);
            response = "Welcome";
        } catch (IndexOutOfBoundsException e) {
            response = "Error during login procedure";
        } catch (ServiceException e) {
            response = "Error during login procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}