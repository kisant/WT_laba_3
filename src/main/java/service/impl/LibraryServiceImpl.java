package service.impl;

import bean.Edition;
import bean.enums.EditionType;
import dao.EditionDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import service.EditionComparatorProvider;
import service.LibraryService;
import service.exeption.ServiceException;

import java.util.List;

/**
 * The type Library service.
 */
public class LibraryServiceImpl implements LibraryService {

    @Override
    public void addNewEdition(Edition edition) throws ServiceException {
        if (edition == null) {
            throw new ServiceException("Unknown edition's type");
        }

        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        try {
            dao.addEdition(edition);
        } catch (DAOException e) {
            throw new ServiceException(e);


        }
    }

    @Override
    public void migrate() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        try {
            dao.migrate();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editedEdition(String id, Edition edition) throws ServiceException {
        if (edition == null) {
            throw new ServiceException("Unknown edition's type");
        }

        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        try {
            dao.editedEdition(id, edition);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void deleteEdition(String id, EditionType editionType) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        try {
            dao.deleteEdition(id, editionType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String showEdition() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        String response;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            List<Edition> editions = dao.showEdition();
            for (Edition edition : editions) {
                stringBuilder.append(edition.toString());
                stringBuilder.append('\n');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            response = stringBuilder.toString();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public String sortEdition(String comparatorName) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        EditionDAO dao = factory.getXmlPrintEditionDAO();

        String response;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            List<Edition> editions = dao.showEdition();
            EditionComparatorProvider comparatorProvider = EditionComparatorProvider.getInstance();
            editions.sort(comparatorProvider.getComparator(comparatorName.toUpperCase()));
            for (Edition edition : editions) {
                stringBuilder.append(edition.toString());
                stringBuilder.append('\n');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            response = stringBuilder.toString();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return response;
    }

}