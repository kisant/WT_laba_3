package bean.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum Encyclopedia type.
 */
@XmlType(name = "encyclopediaType")
@XmlEnum
public enum EncyclopediaType {

    /**
     * Nature encyclopedia type.
     */
    NATURE,
    /**
     * Technology encyclopedia type.
     */
    TECHNOLOGY

}