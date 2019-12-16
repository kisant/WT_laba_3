package bean.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum Newspaper type.
 */
@XmlType(name = "newspaperType")
@XmlEnum
public enum NewspaperType {

    /**
     * News newspaper type.
     */
    NEWS,
    /**
     * Promotional newspaper type.
     */
    PROMOTIONAL,
    /**
     * Yellow newspaper type.
     */
    YELLOW

}