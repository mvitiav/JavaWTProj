package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class Driver implements Serializable {
    private int id;
    private int userId;
    private int diuspId;
    private float wage;
    private int truck_id;

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", userId=" + userId +
                ", diuspId=" + diuspId +
                ", wage=" + wage +
                ", truck_id =" + truck_id +
                '}';
    }

    public Driver(int id, int userId, int diuspId, float wage, int truck_id) {
        this.id = id;
        this.userId = userId;
        this.diuspId = diuspId;
        this.wage = wage;
        this.truck_id = truck_id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDiuspId() {
        return diuspId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setTruck(Truck truck) {
        this.truck_id = truck.getId();
    }

    public void setTruck(int truck_id) {this.truck_id = truck_id;}

    public void setWage(float wage) {
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public float getWage() {
        return wage;
    }

    public int getTruckId() {
        return truck_id;
    }
}
