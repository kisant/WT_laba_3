package service.impl;

import bean.User;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import dao.UserDAO;
import service.ClientService;
import service.exeption.LoginException;
import service.exeption.PasswordException;
import service.exeption.ServiceException;

import java.util.ArrayList;

/**
 * The type Client service.
 */
public class ClientServiceImpl implements ClientService {

    private static final int VALID_LOGIN_LENGTH = 3;
    private static final int VALID_PASSWORD_LENGTH = 8;
    private static final int VALID_MIN_AGE = 14;

    private ArrayList<User> loginUsers = new ArrayList<>();

    @Override
    public void signIn(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty()) {
            throw new LoginException("Incorrect login");
        }
        if (password == null || password.isEmpty()) {
            throw new PasswordException("Incorrect password");
        }
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO dao = factory.getXmlUserDAO();

        try {
            for (User user : loginUsers) {
                if (user.getLogin().equals(login)) {
                    throw new ServiceException(login + " already signIn");
                }
            }
            User loginUser = dao.signIn(login, password);
            loginUsers.add(loginUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signOut(final String login) throws ServiceException {
        if (login == null || login.isEmpty()) {
            throw new LoginException("Incorrect login");
        }
        boolean isSignOut = false;
        for (User loginUser : loginUsers) {
            if (loginUser.getLogin().equals(login)) {
                isSignOut = loginUsers.remove(loginUser);
                break;
            }
        }
        if (!isSignOut) {
            throw new LoginException("Incorrect login");
        }
    }

    @Override
    public void registration(User user) throws ServiceException {
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            throw new ServiceException("Incorrect user's data");
        }
        if (user.getLogin().length() < VALID_LOGIN_LENGTH) {
            throw new LoginException("Login must be at least " + VALID_LOGIN_LENGTH + " symbols");
        }
        if (user.getPassword().length() < VALID_PASSWORD_LENGTH) {
            throw new PasswordException("Password must be at least " + VALID_PASSWORD_LENGTH + " symbols");
        }
        if (user.getAge() < VALID_MIN_AGE) {
            throw new ServiceException("Incorrect age. You must be at least " + VALID_MIN_AGE + " years old");
        }
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO dao = factory.getXmlUserDAO();

        try {
            dao.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}