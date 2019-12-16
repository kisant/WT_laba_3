package dao;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * The type Xml validator by xsd.
 */
public class XMLValidatorByXSD implements XMLValidator {

    private String xsdFilename;

    /**
     * Instantiates a new Xml validator by xsd.
     *
     * @param xsdFilename the xsd filename
     */
    public XMLValidatorByXSD(String xsdFilename) {
        this.xsdFilename = xsdFilename;
    }

    @Override
    public boolean isValid(String xmlFileName) {
        boolean result = true;
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File xsdFile = new File(xsdFilename);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            File xmlFile = new File(xmlFileName);
            StreamSource streamSource = new StreamSource(xmlFile);
            validator.validate(streamSource);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}