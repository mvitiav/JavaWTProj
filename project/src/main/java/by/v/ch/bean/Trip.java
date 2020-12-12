package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class Trip implements Serializable {

    private int id;
    private Driver driver;
    private Order order;
    private boolean isFinished;

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", driver=" + driver +
                ", order=" + order +
                ", isFinished=" + isFinished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                isFinished == trip.isFinished;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFinished);
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Trip(int id, Driver driver, Order order, boolean isFinished) {
        this.id = id;
        this.driver = driver;
        this.order = order;
        this.isFinished = isFinished;
    }
}
