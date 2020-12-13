package by.v.ch.dao;

import by.v.ch.bean.Client;
import by.v.ch.bean.User;

public interface ClientDao {

//    public Client getById(int user_id);
    public Client getByUser(User user);

    public boolean addNew(User user);

}
