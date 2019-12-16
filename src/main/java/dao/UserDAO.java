package dao;

import bean.User;
import dao.exception.DAOException;

/**
 * The interface User dao.
 */
public interface UserDAO {

    /**
     * Sign in user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws DAOException the dao exception
     */
    User signIn(String login, String password) throws DAOException;

    /**
     * Registration.
     *
     * @param user the user
     * @throws DAOException the dao exception
     */
    void registration(User user) throws DAOException;

}