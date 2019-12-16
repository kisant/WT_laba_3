package dao.factory;

import dao.EditionDAO;
import dao.UserDAO;
import dao.XMLValidatorByXSD;
import dao.impl.*;

/**
 * The type Dao factory.
 */
public final class DAOFactory {

    private static final String EDITION_XSD_PATH = "D:\\library\\src\\main\\resources\\editions.xsd";
    private static final String USER_XSD_PATH = "D:\\library\\src\\main\\resources\\users.xsd";
    private static final String EDITION_XML_PATH = "D:\\library\\src\\main\\resources\\editions.xml";
    private static final String USER_XML_PATH = "D:\\library\\src\\main\\resources\\users.xml";
    private static final DAOFactory instance = new DAOFactory();
    private final EditionDAO xmlPrintEditionDAO =
//            new SQLEditionDAO("library", "root", "");
            new XMLEditionDAO(new XMLValidatorByXSD(EDITION_XSD_PATH), EDITION_XML_PATH);
    private final UserDAO xmlUserDAO = new XMLUserDAO(new XMLValidatorByXSD(USER_XSD_PATH), USER_XML_PATH);

    private DAOFactory() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Gets xml print edition dao.
     *
     * @return the xml print edition dao
     */
    public EditionDAO getXmlPrintEditionDAO() {
        return xmlPrintEditionDAO;
    }

    /**
     * Gets xml user dao.
     *
     * @return the xml user dao
     */
    public UserDAO getXmlUserDAO() {
        return xmlUserDAO;
    }

}