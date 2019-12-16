package service;

import bean.User;
import service.exeption.ServiceException;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Sign in.
     *
     * @param login    the login
     * @param password the password
     * @throws ServiceException the service exception
     */
    void signIn(String login, String password) throws ServiceException;

    /**
     * Sign out.
     *
     * @param login the login
     * @throws ServiceException the service exception
     */
    void signOut(String login) throws ServiceException;

    /**
     * Registration.
     *
     * @param user the user
     * @throws ServiceException the service exception
     */
    void registration(User user) throws ServiceException;

}