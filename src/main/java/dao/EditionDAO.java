package dao;

import bean.Edition;
import bean.enums.EditionType;
import dao.exception.DAOException;

import java.util.List;

/**
 * The interface Edition dao.
 */
public interface EditionDAO {

    /**
     * Add edition.
     *
     * @param edition the edition
     * @throws DAOException the dao exception
     */
    void addEdition(Edition edition) throws DAOException;

    /**
     * Migrate.
     *
     * @throws DAOException the dao exception
     */
    void migrate() throws DAOException;

    /**
     * Edited edition.
     *
     * @param id      the id
     * @param edition the edition
     * @throws DAOException the dao exception
     */
    void editedEdition(String id, Edition edition) throws DAOException;

    /**
     * Delete edition.
     *
     * @param id          the id
     * @param editionType the edition type
     * @throws DAOException the dao exception
     */
    void deleteEdition(String id, EditionType editionType) throws DAOException;

    /**
     * Show edition list.
     *
     * @return the list
     * @throws DAOException the dao exception
     */
    List<Edition> showEdition() throws DAOException;

}