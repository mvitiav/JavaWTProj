package by.v.ch.dao;

import by.v.ch.bean.Client;

public interface ClientDao {

    public Client getById(int user_id);

    public boolean addNew(int user_id);

}
