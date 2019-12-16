package dao.impl;

import bean.*;
import bean.enums.*;
import dao.EditionDAO;
import dao.Ferryman;
import dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Xml edition dao.
 */
public class SQLEditionDAO implements EditionDAO {

    private final static String MYSQL_DB_URL = "jdbc:mysql://127.0.0.1/";

    private String mysqlDbName;
    private String mysqlUserLogin;
    private String mysqlUserPassword;


    public SQLEditionDAO(String mysqlDbName, String mysqlUserLogin, String mysqlUserPassword) {
        this.mysqlDbName = mysqlDbName;
        this.mysqlUserLogin = mysqlUserLogin;
        this.mysqlUserPassword = mysqlUserPassword;
    }

    @Override
    public void addEdition(Edition edition) throws DAOException {
        Ferryman ferryman = new Ferryman(mysqlDbName, mysqlUserLogin, mysqlUserPassword);
        Map<String, String> values = new HashMap<>();
        values.put("Title", edition.getTitle());
        try {
            switch (edition.getEditionType()) {
                case PRINT_EDITION:
                    values.put("ListFormat", ((PrintEdition) edition).getListFormat().toString());
                    values.put("ListCount", String.valueOf(((PrintEdition) edition).getListCount()));
                    values.put("PublicationYear", String.valueOf(((PrintEdition) edition).getPublicationYear()));
                    ferryman.migrate("printeditions", values);
                    break;
                case BOOK:
                    values.put("ListFormat", ((PrintEdition) edition).getListFormat().toString());
                    values.put("ListCount", String.valueOf(((PrintEdition) edition).getListCount()));
                    values.put("PublicationYear", String.valueOf(((PrintEdition) edition).getPublicationYear()));
                    values.put("Genre", ((Book) edition).getGenre().toString());
                    ferryman.migrate("books", values);
                    break;
                case NEWSPAPER:
                    values.put("ListFormat", ((PrintEdition) edition).getListFormat().toString());
                    values.put("ListCount", String.valueOf(((PrintEdition) edition).getListCount()));
                    values.put("PublicationYear", String.valueOf(((PrintEdition) edition).getPublicationYear()));
                    values.put("NewspaperType", ((Newspaper) edition).getNewspaperType().toString());
                    ferryman.migrate("newspapers", values);
                    break;
                case ENCYCLOPEDIA:
                    values.put("ListFormat", ((PrintEdition) edition).getListFormat().toString());
                    values.put("ListCount", String.valueOf(((PrintEdition) edition).getListCount()));
                    values.put("PublicationYear", String.valueOf(((PrintEdition) edition).getPublicationYear()));
                    values.put("EncyclopediaType", ((Encyclopedia) edition).getEncyclopediaType().toString());
                    ferryman.migrate("encyclopedias", values);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void migrate() throws DAOException {
        throw new DAOException("Not supported");
    }

    @Override
    public void editedEdition(String id, Edition editedEdition) throws DAOException {
        deleteEdition(id, editedEdition.getEditionType());
        addEdition(editedEdition);
    }

    @Override
    public void deleteEdition(String id, EditionType editionType) throws DAOException {
        Ferryman ferryman = new Ferryman(mysqlDbName, mysqlUserLogin, mysqlUserPassword);
        try {
            switch (editionType) {
                case PRINT_EDITION:
                    ferryman.deleteByID("printeditions", id);
                    break;
                case BOOK:
                    ferryman.deleteByID("books", id);
                    break;
                case NEWSPAPER:
                    ferryman.deleteByID("newspapers", id);
                    break;
                case ENCYCLOPEDIA:
                    ferryman.deleteByID("encyclopedias", id);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Edition> showEdition() throws DAOException {
        ArrayList<Edition> editions = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(MYSQL_DB_URL + mysqlDbName, mysqlUserLogin, mysqlUserPassword);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `printeditions`");
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String title = resultSet.getString("Title");
                ListFormat listFormat = ListFormat.valueOf(resultSet.getString("ListFormat"));
                int listCount = resultSet.getInt("ListCount");
                int publicationYear = resultSet.getInt("PublicationYear");
                Edition edition = new PrintEdition(EditionType.PRINT_EDITION, id, title, listFormat, listCount, publicationYear);
            }
            resultSet = statement.executeQuery("SELECT * FROM `books`");
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String title = resultSet.getString("Title");
                ListFormat listFormat = ListFormat.valueOf(resultSet.getString("ListFormat"));
                int listCount = resultSet.getInt("ListCount");
                int publicationYear = resultSet.getInt("PublicationYear");
                Genre genre = Genre.valueOf(resultSet.getString("Genre"));
                Edition edition = new Book(EditionType.BOOK, id, title, listFormat, listCount, publicationYear, genre);
                editions.add(edition);
            }
            resultSet = statement.executeQuery("SELECT * FROM `newspapers`");
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String title = resultSet.getString("Title");
                ListFormat listFormat = ListFormat.valueOf(resultSet.getString("ListFormat"));
                int listCount = resultSet.getInt("ListCount");
                int publicationYear = resultSet.getInt("PublicationYear");
                NewspaperType newspaperType = NewspaperType.valueOf(resultSet.getString("NewspaperType"));
                Edition edition = new Newspaper(EditionType.NEWSPAPER, id, title, listFormat, listCount, publicationYear, newspaperType);
                editions.add(edition);
            }
            resultSet = statement.executeQuery("SELECT * FROM `encyclopedias`");
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String title = resultSet.getString("Title");
                ListFormat listFormat = ListFormat.valueOf(resultSet.getString("ListFormat"));
                int listCount = resultSet.getInt("ListCount");
                int publicationYear = resultSet.getInt("PublicationYear");
                EncyclopediaType encyclopediaType = EncyclopediaType.valueOf(resultSet.getString("EncyclopediaType"));
                Edition edition = new Encyclopedia(EditionType.ENCYCLOPEDIA, id, title, listFormat, listCount, publicationYear, encyclopediaType);
                editions.add(edition);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return editions;
    }

}