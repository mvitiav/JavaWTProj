package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class TruckType implements Serializable {
   private int typeID;
    private float volume;
    private int capacity;
    private String name;
    private CarPurpose purpose;

    @Override
    public String toString() {
        return "TruckType{" +
                "typeID=" + typeID +
                ", volume=" + volume +
                ", capacity=" + capacity +
                ", name='" + name + '\'' +
                ", purpose=" + purpose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckType truckType = (TruckType) o;
        return typeID == truckType.typeID;
    }

    public CarPurpose getPurpose() {
        return purpose;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeID);
    }

    public String getName() {
        return name;
    }

    public int getTypeID() {
        return typeID;
    }

    public float getVolume() {
        return volume;
    }

    public int getCapacity() {
        return capacity;
    }

    public TruckType(int typeID, float volume, int capacity, String name, CarPurpose purpose) {
        this.typeID = typeID;
        this.volume = volume;
        this.capacity = capacity;
        this.name = name;
        this.purpose = purpose;
    }
}
