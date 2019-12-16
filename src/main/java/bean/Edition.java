package bean;

import bean.enums.EditionType;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

/**
 * The type Edition.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "edition", propOrder = {
        "title",
        "editionType",
})
@XmlSeeAlso({
        PrintEdition.class
})
public abstract class Edition implements Comparable<Edition> {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;
    @XmlElement(name = "title", required = true)
    private String title;
    @XmlElement(name = "edition-type", required = true)
    private EditionType editionType;

    /**
     * Instantiates a new Edition.
     */
    public Edition() {

    }

    /**
     * Instantiates a new Edition.
     *
     * @param editionType the edition type
     * @param id          the id
     * @param title       the title
     */
    public Edition(EditionType editionType, String id, String title) {
        this.editionType = editionType;
        this.id = id;
        this.title = title;
    }

    /**
     * Gets edition type.
     *
     * @return the edition type
     */
    public EditionType getEditionType() {
        return editionType;
    }

    /**
     * Sets edition type.
     *
     * @param editionType the edition type
     */
    @XmlTransient
    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edition edition = (Edition) o;
        return id.equals(edition.id) &&
                title.equals(edition.title) &&
                editionType == edition.editionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, editionType);
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", editionType=" + editionType +
                '}';
    }

    @Override
    public int compareTo(Edition o) {
        return title.compareTo(o.title);
    }

}