package by.v.ch.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private int id;
    private int clientId;
    private float price;
    private String shipmentPoint;
    private String destinationPoint;
    private Date shipmentDate;
    private Date destinationDate;
    private float size;
    private float volume;
    private float weight;
    private CarPurpose purpose;
    private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
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

    public Order(int clientId, String shipmentPoint, String destinationPoint, Date shipmentDate, Date destinationDate, float size, float volume, float weight, CarPurpose purpose) {
        this.clientId = clientId;
        this.shipmentPoint = shipmentPoint;
        this.destinationPoint = destinationPoint;
        this.shipmentDate = shipmentDate;
        this.destinationDate = destinationDate;
        this.size = size;
        this.volume = volume;
        this.weight = weight;
        this.purpose = purpose;
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

    public int getClientId() {
        return clientId;
    }

    public CarPurpose getPurpose() {
        return purpose;
    }

    public Order(int id, int clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setShipmentPoint(String shipmentPoint) {
        this.shipmentPoint = shipmentPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Date getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(Date destinationDate) {
        this.destinationDate = destinationDate;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPurpose(CarPurpose purpose) {
        this.purpose = purpose;
    }

//    public Order(int id, float price, String shipmentPoint, String destinationPoint, Date shipmentDate, float size, float volume, float weight, CarPurpose purpose) {
//        this.id = id;
//        this.price = price;
//        this.shipmentPoint = shipmentPoint;
//        this.destinationPoint = destinationPoint;
//        this.shipmentDate = shipmentDate;
//        this.size = size;
//        this.volume = volume;
//        this.weight = weight;
//        this.purpose = purpose;
//    }
}
