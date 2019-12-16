package bean;

import bean.enums.EditionType;
import bean.enums.ListFormat;
import bean.enums.NewspaperType;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * The type Newspaper.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newspaper", propOrder = {
        "newspaperType"
})
public class Newspaper extends PrintEdition {

    @XmlElement(name = "newspaper-type", required = true)
    @XmlSchemaType(name = "string")
    private NewspaperType newspaperType;

    /**
     * Instantiates a new Newspaper.
     */
    public Newspaper() {
        setEditionType(EditionType.NEWSPAPER);
    }

    /**
     * Instantiates a new Newspaper.
     *
     * @param editionType     the edition type
     * @param id              the id
     * @param title           the title
     * @param listFormat      the list format
     * @param listCount       the list count
     * @param publicationYear the publication year
     * @param newspaperType   the newspaper type
     */
    public Newspaper(EditionType editionType, String id, String title, ListFormat listFormat, int listCount, int publicationYear, NewspaperType newspaperType) {
        super(editionType, id, title, listFormat, listCount, publicationYear);
        this.newspaperType = newspaperType;
    }

    /**
     * Gets newspaper type.
     *
     * @return the newspaper type
     */
    public NewspaperType getNewspaperType() {
        return newspaperType;
    }

    /**
     * Sets newspaper type.
     *
     * @param newspaperType the newspaper type
     */
    public void setNewspaperType(NewspaperType newspaperType) {
        this.newspaperType = newspaperType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return newspaperType == newspaper.newspaperType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), newspaperType);
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "newspaperType=" + newspaperType +
                "} " + super.toString();
    }

}