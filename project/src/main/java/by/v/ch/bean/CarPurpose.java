package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class CarPurpose implements Serializable {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "carPurpose{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPurpose that = (CarPurpose) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CarPurpose(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
