package by.v.ch.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private int id;
    private float price;
    private String shipmentPoint;
    private String destinationPoint;
    private Date shipmentDate;
    private float size;
    private float volume;
    private float weight;
    private CarPurpose purpose;

    //todo: block big strings
    //todo: change to final

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shipmentPoint='" + shipmentPoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", purpose=" + purpose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getShipmentPoint() {
        return shipmentPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public float getSize() {
        return size;
    }

    public float getVolume() {
        return volume;
    }

    public float getWeight() {
        return weight;
    }

    public CarPurpose getPurpose() {
        return purpose;
    }

    public Order(int id, float price, String shipmentPoint, String destinationPoint, Date shipmentDate, float size, float volume, float weight, CarPurpose purpose) {
        this.id = id;
        this.price = price;
        this.shipmentPoint = shipmentPoint;
        this.destinationPoint = destinationPoint;
        this.shipmentDate = shipmentDate;
        this.size = size;
        this.volume = volume;
        this.weight = weight;
        this.purpose = purpose;
    }
}
