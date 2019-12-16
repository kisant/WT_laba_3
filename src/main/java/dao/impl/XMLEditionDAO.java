package dao.impl;

import bean.*;
import bean.enums.EditionType;
import dao.EditionDAO;
import dao.Editions;
import dao.Ferryman;
import dao.XMLValidator;
import dao.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Xml edition dao.
 */
public class XMLEditionDAO implements EditionDAO {

    private String xmlFileName;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
    private XMLValidator xmlValidator;

    /**
     * Instantiates a new Xml edition dao.
     *
     * @param xmlValidator the xml validator
     * @param xmlFileName  the xml file name
     */
    public XMLEditionDAO(XMLValidator xmlValidator, String xmlFileName) {
        this.xmlValidator = xmlValidator;
        this.xmlFileName = xmlFileName;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Editions.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Editions read() throws DAOException {
        if (xmlValidator.isValid(xmlFileName)) {
            try {
                return (Editions) unmarshaller.unmarshal(new File(xmlFileName));
            } catch (JAXBException e) {
                throw new DAOException("Invalid xml");
            }
        }
        throw new DAOException("Invalid xml");
    }

    private void write(Editions editions) {
        try {
            marshaller.marshal(editions, new File(xmlFileName));
        } catch (JAXBException ignored) {

        }
    }

    @Override
    public void addEdition(Edition edition) throws DAOException {
        Editions editions = read();
        for (Edition edition1 : editions.getEditions()) {
            if (edition.getId().equals(edition1.getId())) {
                throw new DAOException("Edition with " + edition.getId() + " id exist");
            }
        }
        editions.getEditions().add(edition);
        write(editions);
    }

    @Override
    public void migrate() throws DAOException {
        Editions editions = read();
        Ferryman ferryman = new Ferryman("library", "root", "");
        for (Edition edition : editions.getEditions()) {
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
    }

    @Override
    public void editedEdition(String id, Edition editedEdition) throws DAOException {
        Editions editions = read();
        for (Edition currEdition : editions.getEditions()) {
            if (editedEdition.getId().equals(currEdition.getId()) && !currEdition.getId().equals(id)) {
                throw new DAOException("Edition with " + editedEdition.getId() + " id exist");
            }
        }
        editions.getEditions().removeIf(edition -> edition.getId().equals(id));
        editions.getEditions().add(editedEdition);
        write(editions);
    }

    @Override
    public void deleteEdition(String id, EditionType editionType) throws DAOException {
        Editions editions = read();
        editions.getEditions().removeIf(edition -> edition.getId().equals(id));
        write(editions);
    }

    @Override
    public List<Edition> showEdition() throws DAOException {
        return read().getEditions();
    }

}