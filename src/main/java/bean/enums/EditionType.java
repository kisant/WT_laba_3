package bean.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum Edition type.
 */
@XmlType(name = "editionType")
@XmlEnum
public enum EditionType {

    /**
     * Print edition edition type.
     */
    PRINT_EDITION,
    /**
     * Book edition type.
     */
    BOOK,
    /**
     * Encyclopedia edition type.
     */
    ENCYCLOPEDIA,
    /**
     * Newspaper edition type.
     */
    NEWSPAPER

}