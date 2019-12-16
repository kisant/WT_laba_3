package bean;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
        "login",
        "password",
        "age"
})
public class User {

    @XmlElement(name = "login", required = true)
    private String login;
    @XmlElement(name = "password", required = true)
    private String password;
    @XmlElement(name = "age", required = true)
    private int age;

    public User() {

    }

    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

}