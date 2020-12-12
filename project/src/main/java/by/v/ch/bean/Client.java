package by.v.ch.bean;

import java.io.Serializable;

public class Client extends User implements Serializable{

    public Client(int id, String username) {
        super(id, username);
    }
}
