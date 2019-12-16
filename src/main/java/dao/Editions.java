package dao;

import bean.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Editions.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "editions", propOrder = {
        "editions"
})
public class Editions {

    @XmlElements({
            @XmlElement(name = "print-edition", type = PrintEdition.class),
            @XmlElement(name = "newspaper", type = Newspaper.class),
            @XmlElement(name = "encyclopedia", type = Encyclopedia.class),
            @XmlElement(name = "book", type = Book.class),
    })
    private List<Edition> editions = new ArrayList<>();

    /**
     * Gets editions.
     *
     * @return the editions
     */
    public List<Edition> getEditions() {
        return editions;
    }

    @Override
    public String toString() {
        return "Editions{" +
                "editions=" + editions +
                '}';
    }

}