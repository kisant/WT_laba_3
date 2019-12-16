package dao.parser.factory;

import dao.Editions;
import dao.parser.XmlParser;
import dao.parser.exception.ParserException;
import dao.parser.impl.EditionDomParser;
import dao.parser.impl.EditionJaxbParser;
import dao.parser.impl.EditionSaxParser;
import dao.parser.validator.XmlValidator;

/**
 * The type Parser factory.
 */
public class ParserFactory {

    private static ParserFactory instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }

        return instance;
    }

    private ParserFactory() {

    }

    /**
     * New parser xml parser.
     *
     * @param parserType the parser type
     * @param validator  the validator
     * @return the xml parser
     * @throws ParserException the parser exception
     */
    public XmlParser<Editions> newParser(String parserType, XmlValidator validator) throws ParserException {
        XmlParser<Editions> parser;

        switch (parserType) {
            case ParserConstant.DOM:
                parser = new EditionDomParser(validator);
                break;
            case ParserConstant.SAX:
                parser = new EditionSaxParser(validator);
                break;
            case ParserConstant.JAXB:
                parser = new EditionJaxbParser(validator);
                break;
            default:
                throw new ParserException("Parser type not found");
        }

        return parser;
    }

}