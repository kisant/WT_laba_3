package bean.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum List format.
 */
@XmlType(name = "listFormat")
@XmlEnum
public enum ListFormat {

    /**
     * A 1 list format.
     */
    A1,
    /**
     * A 2 list format.
     */
    A2,
    /**
     * A 3 list format.
     */
    A3,
    /**
     * A 4 list format.
     */
    A4

}