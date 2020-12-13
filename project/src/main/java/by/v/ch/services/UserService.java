package by.v.ch.services;

import by.v.ch.bean.User;

public interface UserService extends myService{
    public User logIn(String username,String password);
//    public boolean register(User user, String username,int password);
//    public boolean register(String username,String password);
    public boolean register(String username,String password,User.Role role);
}
