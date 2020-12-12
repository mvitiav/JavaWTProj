package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String username;

    //todo: change string ty safwe type from java.security

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


}
