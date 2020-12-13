package by.v.ch.dao;

import by.v.ch.bean.Dispatcher;
import by.v.ch.bean.User;

public interface DispatcherDao {

    public Dispatcher getByUser(User user);

    public boolean addNew(User user);

}
