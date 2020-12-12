package by.v.ch.dao;

import by.v.ch.bean.Order;

public interface OrderDao {

    public by.v.ch.bean.Order getOrderById(int id);

    public boolean addOrder(Order order);

    public boolean updateOrder(Order order);

    public Order[] getNewOrders(int maxcount);


}
