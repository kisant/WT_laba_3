package dao;

import bean.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Users.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "users", propOrder = {
        "users"
})
public class Users {

    @XmlElements({
            @XmlElement(name = "user", type = User.class),
    })
    private List<User> users = new ArrayList<>();

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

}