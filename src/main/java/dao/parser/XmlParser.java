package dao.parser;

import dao.parser.exception.ParserException;

/**
 * The interface Xml parser.
 *
 * @param <T> the type parameter
 */
public interface XmlParser<T> {

    /**
     * Parse t.
     *
     * @param pathToXml the path to xml
     * @return the t
     * @throws ParserException the parser exception
     */
    T parse(String pathToXml) throws ParserException;

}