package controller.command.impl;

import bean.User;
import controller.command.Command;
import service.ClientService;
import service.exeption.ServiceException;
import service.factory.ServiceFactory;

/**
 * The type Registration.
 */
public class Registration implements Command {

    @Override
    public String execute(String request) {
        String response;
        try {
            int indexStart = request.indexOf(paramDelimiter) + 1;
            int indexEnd = request.indexOf(paramDelimiter, indexStart);
            String login = request.substring(indexStart, indexEnd);

            indexStart = indexEnd + 1;
            indexEnd = request.indexOf(paramDelimiter, indexStart);
            String password = request.substring(indexStart, indexEnd);

            indexEnd++;
            int age = Integer.valueOf(request.substring(indexEnd));

            ServiceFactory factory = ServiceFactory.getInstance();
            ClientService service = factory.getClientService();

            service.registration(new User(login, password, age));
            response = "Registration completed";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            response = "Error during registration procedure";
        } catch (ServiceException e) {
            response = "Error during registration procedure. " + e.getMessage().substring(e.getMessage().indexOf(paramDelimiter) + 1);
        }

        return response;
    }

}