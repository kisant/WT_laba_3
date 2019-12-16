package dao.parser.validator;

import dao.parser.exception.ParserException;

/**
 * The interface Xml validator.
 */
public interface XmlValidator {

    /**
     * Is valid boolean.
     *
     * @param pathToXml the path to xml
     * @return the boolean
     * @throws ParserException the parser exception
     */
    boolean isValid(String pathToXml) throws ParserException;

}