package dao.parser.validator.impl;

import dao.parser.exception.ParserException;
import dao.parser.validator.XmlValidator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * The type Xml validator by xsd.
 */
public class XmlValidatorByXsd implements XmlValidator {

    private String pathToXsd;

    /**
     * Instantiates a new Xml validator by xsd.
     *
     * @param pathToXsd the path to xsd
     */
    public XmlValidatorByXsd(String pathToXsd) {
        this.pathToXsd = pathToXsd;
    }

    public boolean isValid(String pathToXml) throws ParserException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema;
        try {
            File xsdFile = new File(pathToXsd);
            schema = schemaFactory.newSchema(xsdFile);
        } catch (SAXException e) {
            throw new ParserException(e);
        }

        boolean result = true;
        try {
            Validator validator = schema.newValidator();
            File xmlFile = new File(pathToXml);
            StreamSource streamSource = new StreamSource(xmlFile);
            validator.validate(streamSource);
        } catch (SAXException e) {
            result = false;
        } catch (IOException e) {
            throw new ParserException(e);
        }

        return result;
    }

    /**
     * Gets path to xsd.
     *
     * @return the path to xsd
     */
    public String getPathToXsd() {
        return pathToXsd;
    }

}