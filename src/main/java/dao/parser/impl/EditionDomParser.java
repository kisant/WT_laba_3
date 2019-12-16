package dao.parser.impl;

import bean.*;
import bean.enums.*;
import dao.Editions;
import dao.parser.XmlParser;
import dao.parser.exception.ParserException;
import dao.parser.validator.XmlValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

/**
 * The type Edition dom parser.
 */
public class EditionDomParser implements XmlParser<Editions> {

    private XmlValidator validator;

    /**
     * Instantiates a new Edition dom parser.
     *
     * @param validator the validator
     */
    public EditionDomParser(XmlValidator validator) {
        this.validator = validator;
    }

    public Editions parse(String pathToXml) throws ParserException {
        if (!validator.isValid(pathToXml)) {
            throw new ParserException("XML doesn't match XSD");
        }

        Editions candies = new Editions();
        List<Edition> list = candies.getEditions();
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            document = builder.parse(pathToXml);

            Element root = document.getDocumentElement();
            NodeList printEditionList = root.getElementsByTagName(EditionXmlElementName.PRINT_EDITION_TAG);
            handleElements(list, printEditionList);

            NodeList bookList = root.getElementsByTagName(EditionXmlElementName.BOOK_TAG);
            handleElements(list, bookList);

            NodeList newspaperList = root.getElementsByTagName(EditionXmlElementName.NEWSPAPER_TAG);
            handleElements(list, newspaperList);

            NodeList encyclopediaList = root.getElementsByTagName(EditionXmlElementName.ENCYCLOPEDIA_TAG);
            handleElements(list, encyclopediaList);


        } catch (Exception e) {
            throw new ParserException(e);
        }

        return candies;
    }

    private void handleElements(List<Edition> list, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element child = (Element) nodeList.item(i);
            Edition edition = createEdition(child);
            list.add(edition);
        }
    }

    private static Edition createEdition(Element child) {
        String id = child.getAttribute(EditionXmlElementName.ID_ATTRIBUTE_NAME);
        String title = extractStringValue(child, EditionXmlElementName.TITLE_TAG);
        EditionType editionType = EditionType.valueOf(extractStringValue(child, EditionXmlElementName.EDITION_TYPE_TAG));

        ListFormat listFormat = ListFormat.valueOf(extractStringValue(child, EditionXmlElementName.LIST_FORMAT_TAG));
        int listCount = extractIntValue(child, EditionXmlElementName.LIST_COUNT_TAG);
        int publicationYear = extractIntValue(child, EditionXmlElementName.PUBLICATION_YEAR_TAG);

        Edition edition;
        String childName = child.getTagName();
        switch (childName) {
            case EditionXmlElementName.PRINT_EDITION_TAG:
                edition = new PrintEdition(editionType, id, title, listFormat, listCount, publicationYear);
                break;
            case EditionXmlElementName.BOOK_TAG:
                Genre genre = Genre.valueOf(extractStringValue(child, EditionXmlElementName.GENRE_TAG));
                edition = new Book(editionType, id, title, listFormat, listCount, publicationYear, genre);
                break;
            case EditionXmlElementName.ENCYCLOPEDIA_TAG:
                EncyclopediaType encyclopediaType = EncyclopediaType.valueOf(extractStringValue(child, EditionXmlElementName.ENCYCLOPEDIA_TYPE_TAG));
                edition = new Encyclopedia(editionType, id, title, listFormat, listCount, publicationYear, encyclopediaType);
                break;
            case EditionXmlElementName.NEWSPAPER_TAG:
                NewspaperType newspaperType = NewspaperType.valueOf(extractStringValue(child, EditionXmlElementName.NEWSPAPER_TYPE_TAG));
                edition = new Newspaper(editionType, id, title, listFormat, listCount, publicationYear, newspaperType);
                break;
            default:
                edition = null;
                break;
        }

        return edition;
    }

    private static String extractStringValue(Element element, String name) {
        NodeList list = element.getElementsByTagName(name);
        Node node = list.item(0);
        return node.getTextContent();
    }

    private static int extractIntValue(Element element, String name) {
        String value = extractStringValue(element, name);
        return Integer.parseInt(value);
    }

    /**
     * Gets validator.
     *
     * @return the validator
     */
    public XmlValidator getValidator() {
        return validator;
    }

}