package bean.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum Genre.
 */
@XmlType(name = "genre")
@XmlEnum
public enum  Genre {

    /**
     * Fantasy genre.
     */
    FANTASY,
    /**
     * Mystic genre.
     */
    MYSTIC,
    /**
     * Detective genre.
     */
    DETECTIVE

}