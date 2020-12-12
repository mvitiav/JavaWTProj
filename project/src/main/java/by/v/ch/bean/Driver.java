package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class Driver extends User implements Serializable {
    private int id;
    private String name;
    private float wage;
    private Truck truck;


    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Driver(int userid, String username, int driverId, String name, float wage, Truck truck) {
        super(userid, username);
        this.id = driverId;
        this.name = name;
        this.wage = wage;
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getWage() {
        return wage;
    }

    public Truck getTruck() {
        return truck;
    }
}
