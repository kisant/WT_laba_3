package dao.parser.impl;

import bean.*;
import bean.enums.*;
import dao.Editions;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * The type Edition content handler.
 */
public class EditionContentHandler implements ContentHandler {

    private Editions editions;
    private String id;
    private String title;
    private EditionType editionType;
    private ListFormat listFormat;
    private int listCount;
    private int publicationYear;
    private NewspaperType newspaperType;
    private EncyclopediaType encyclopediaType;
    private Genre genre;

    private StringBuilder builder = new StringBuilder();

    /**
     * Instantiates a new Edition content handler.
     *
     * @param editions the editions
     */
    public EditionContentHandler(Editions editions) {
        this.editions = editions;
    }

    public void setDocumentLocator(Locator locator) {

    }

    public void startDocument() throws SAXException {

    }

    public void endDocument() throws SAXException {

    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    public void endPrefixMapping(String prefix) throws SAXException {

    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        for (int i = 0; i < atts.getLength(); i++) {
            if (EditionXmlElementName.ID_ATTRIBUTE_NAME.equals(atts.getLocalName(i))) {
                id = atts.getValue(i);
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        List<Edition> list = editions.getEditions();
        String value = builder.toString();
        value = value.trim();
        switch (localName) {
            case EditionXmlElementName.PRINT_EDITION_TAG:
                PrintEdition printEdition = new PrintEdition(editionType, id, title, listFormat, listCount, publicationYear);
                list.add(printEdition);
                break;
            case EditionXmlElementName.BOOK_TAG:
                Book book = new Book(editionType, id, title, listFormat, listCount, publicationYear, genre);
                list.add(book);
                break;
            case EditionXmlElementName.ENCYCLOPEDIA_TAG:
                Encyclopedia encyclopedia = new Encyclopedia(editionType, id, title, listFormat, listCount, publicationYear, encyclopediaType);
                list.add(encyclopedia);
                break;
            case EditionXmlElementName.NEWSPAPER_TAG:
                Newspaper newspaper = new Newspaper(editionType, id, title, listFormat, listCount, publicationYear, newspaperType);
                list.add(newspaper);
                break;
            case EditionXmlElementName.TITLE_TAG:
                title = value;
                break;
            case EditionXmlElementName.EDITION_TYPE_TAG:
                editionType = EditionType.valueOf(value);
                break;
            case EditionXmlElementName.LIST_FORMAT_TAG:
                listFormat = ListFormat.valueOf(value);
                break;
            case EditionXmlElementName.LIST_COUNT_TAG:
                listCount = Integer.valueOf(value);
                break;
            case EditionXmlElementName.PUBLICATION_YEAR_TAG:
                publicationYear = Integer.valueOf(value);
                break;
            case EditionXmlElementName.ENCYCLOPEDIA_TYPE_TAG:
                encyclopediaType = EncyclopediaType.valueOf(value);
                break;
            case EditionXmlElementName.GENRE_TAG:
                genre = Genre.valueOf(value);
                break;
            case EditionXmlElementName.NEWSPAPER_TYPE_TAG:
                newspaperType = NewspaperType.valueOf(value);
                break;
        }

        builder = new StringBuilder();
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        builder.append(ch, start, length);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    public void processingInstruction(String target, String data) throws SAXException {

    }

    public void skippedEntity(String name) throws SAXException {

    }

}