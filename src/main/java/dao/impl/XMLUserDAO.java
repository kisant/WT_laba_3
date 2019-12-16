package dao.impl;

import bean.User;
import dao.UserDAO;
import dao.Users;
import dao.XMLValidator;
import dao.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The type Xml user dao.
 */
public class XMLUserDAO implements UserDAO {

    private String xmlFileName;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
    private XMLValidator xmlValidator;


    /**
     * Instantiates a new Xml user dao.
     *
     * @param xmlValidator the xml validator
     * @param xmlFileName  the xml file name
     */
    public XMLUserDAO(XMLValidator xmlValidator, String xmlFileName) {
        this.xmlValidator = xmlValidator;
        this.xmlFileName = xmlFileName;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Users read() throws DAOException {
        if (xmlValidator.isValid(xmlFileName)) {
            try {
                return (Users) unmarshaller.unmarshal(new File(xmlFileName));
            } catch (JAXBException e) {
                throw new DAOException("Invalid xml");
            }
        }
        throw new DAOException("Invalid xml");
    }

    private void write(Users users) {
        try {
            marshaller.marshal(users, new File(xmlFileName));
        } catch (JAXBException ignored) {

        }
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        Users users = read();
        for (User user : users.getUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new DAOException("Incorrect login or password");
    }

    @Override
    public void registration(User user) throws DAOException {
        Users users = read();
        for (User u : users.getUsers()) {
            if (u.getLogin().equals(user.getLogin())) {
                throw new DAOException("This login already exist");
            }
        }
        users.getUsers().add(user);
        write(users);
    }

}