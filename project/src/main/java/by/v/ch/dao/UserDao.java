package by.v.ch.dao;

import by.v.ch.bean.User;

public interface UserDao {
    //todo: change to exception
    public boolean register(String name, String pass);

    public User logIn(String name,String pass);

}
