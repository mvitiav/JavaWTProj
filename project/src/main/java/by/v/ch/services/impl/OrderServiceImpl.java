package by.v.ch.services.impl;

import by.v.ch.bean.CarPurpose;
import by.v.ch.bean.Client;
import by.v.ch.bean.Order;
import by.v.ch.bean.User;
import by.v.ch.dao.ClientDao;
import by.v.ch.dao.PurposeDao;
import by.v.ch.dao.UserDao;
import by.v.ch.dao.factory.DaoFactory;
import by.v.ch.services.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addOrder(User user, float size, float volume, float weight, Date shipmentDate, Date destinationDate, String shipmentpoint, String destinationpoint,int purposeId) {
        DaoFactory daoFactory=DaoFactory.getInstance();

        Client client = daoFactory.getClientDao().getById(user.getId());

        CarPurpose carPurpose =daoFactory.getPurposeDao().getById(purposeId);
        //int clientId, String shipmentPoint, String destinationPoint, Date shipmentDate, Date destinationDate, float size, float volume, float weight, CarPurpose purpose
       return daoFactory.getOrderDao().addOrder(new Order(client.getClientId(),shipmentpoint,destinationpoint,shipmentDate,destinationDate,size,volume,weight,carPurpose));
//        ClientDao clientDao = daoFactory.getClientDao();
//        return clientDao
    }

    @Override
    public Order[] getOrdersOfUser(User user) {
        DaoFactory daoFactory=DaoFactory.getInstance();
        Client client = daoFactory.getClientDao().getById(user.getId());
        Order[] orders= daoFactory.getOrderDao().getOrdersOfClient(client);
        //todo:change status of order from TripDao
        //todo: create TripDao
        return orders;
    }

    @Override
    public Order[] getUnsetOrders() {
        DaoFactory daoFactory=DaoFactory.getInstance();
        return daoFactory.getOrderDao().getUnsetOrders();
    }


}
