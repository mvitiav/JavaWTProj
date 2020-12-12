package by.v.ch.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//todo: add type of truck
public class Truck implements Serializable {
    private int id;
    private Date lastInspection;
    private float mileage;
    private float mileageBeforeInspection;
    private TruckType truckType;


    public TruckType getTruckType() {
        return truckType;
    }

    public Truck(int id, Date lastInspection, float mileage, float mileageBeforeInspection, TruckType truckType) {
        this.id = id;
        this.lastInspection = lastInspection;
        this.mileage = mileage;
        this.mileageBeforeInspection = mileageBeforeInspection;
        this.truckType = truckType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return id == truck.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", truckType=" + truckType +
                '}';
    }

    public int getId() {
        return id;
    }

    public Date getLastInspection() {
        return lastInspection;
    }

    public float getMileage() {
        return mileage;
    }

    public float getMileageBeforeInspection() {
        return mileageBeforeInspection;
    }
}
