package service;

import bean.Edition;
import bean.enums.EditionType;
import service.exeption.ServiceException;

/**
 * The interface Library service.
 */
public interface LibraryService {

    /**
     * Add new edition.
     *
     * @param edition the edition
     * @throws ServiceException the service exception
     */
    void addNewEdition(Edition edition) throws ServiceException;

    /**
     * Migrate.
     *
     * @throws ServiceException the service exception
     */
    void migrate() throws ServiceException;

    /**
     * Edited edition.
     *
     * @param id      the id
     * @param edition the edition
     * @throws ServiceException the service exception
     */
    void editedEdition(String id, Edition edition) throws ServiceException;

    /**
     * Delete edition.
     *
     * @param id          the id
     * @param editionType the edition type
     * @throws ServiceException the service exception
     */
    void deleteEdition(String id, EditionType editionType) throws ServiceException;

    /**
     * Show edition string.
     *
     * @return the string
     * @throws ServiceException the service exception
     */
    String showEdition() throws ServiceException;

    /**
     * Sort edition string.
     *
     * @param comparatorName the comparator name
     * @return the string
     * @throws ServiceException the service exception
     */
    String sortEdition(String comparatorName) throws ServiceException;

}