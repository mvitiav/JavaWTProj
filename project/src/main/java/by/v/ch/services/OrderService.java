package by.v.ch.services;

import by.v.ch.bean.Order;
import by.v.ch.bean.User;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.Date;

public interface OrderService {
    public boolean addOrder(User user, float size, float volume, float weight, Date shipmentDate, Date destinationDate, String shipmentpoint, String destinationpoint,int purposeId);

    public Order getById(int id);

    public Order[] getOrdersOfUser(User user);

    public Order[] getUnsetOrders();


}
