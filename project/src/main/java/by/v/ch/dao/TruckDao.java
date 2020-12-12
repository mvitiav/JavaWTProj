package by.v.ch.dao;

import by.v.ch.bean.Truck;

public interface TruckDao {

    public Truck getTruckById(int id);

    public boolean addTruck(Truck truck);

    public boolean  updateTruck(Truck truck);

}
