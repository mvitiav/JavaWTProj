package by.v.ch.dao;

import by.v.ch.bean.Dispatcher;
import by.v.ch.bean.Driver;
import by.v.ch.bean.User;


public interface DriverDao {

    public Driver getDriverById(int user_id);

    public boolean addDriver(User user);

    public Driver[] getDriversOfDispatcher(Dispatcher dispatcher);

}
