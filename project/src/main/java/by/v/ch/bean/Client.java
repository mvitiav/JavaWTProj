package by.v.ch.bean;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable{
private int clientId;
private int userId;

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId &&
                userId == client.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, userId);
    }

    public int getClientId() {
        return clientId;
    }

    public int getUserId() {
        return userId;
    }

    public Client(int clientId, int userId) {
        this.clientId = clientId;
        this.userId = userId;
    }

    public Client(int id) {

    }
}
