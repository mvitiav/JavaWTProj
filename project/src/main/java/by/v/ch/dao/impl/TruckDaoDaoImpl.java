package by.v.ch.dao.impl;


import by.v.ch.bean.Truck;
import by.v.ch.dao.TruckDao;
import by.v.ch.dao.connection.ConnectionFactory;

public class TruckDaoDaoImpl implements TruckDao {

    private static final ConnectionFactory connectionPool = ConnectionFactory.getInstance();


    @Override
    public Truck getTruckById(int id) {
        return null;
    }

    @Override
    public boolean addTruck(Truck truck) {
        return false;
    }

    @Override
    public boolean updateTruck(Truck truck) {
        return false;
    }
}
