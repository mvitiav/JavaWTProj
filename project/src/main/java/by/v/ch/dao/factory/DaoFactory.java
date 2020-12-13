package by.v.ch.dao.factory;

import by.v.ch.dao.ClientDao;
import by.v.ch.dao.OrderDao;
import by.v.ch.dao.PurposeDao;
import by.v.ch.dao.UserDao;
import by.v.ch.dao.impl.ClientDaoImpl;
import by.v.ch.dao.impl.OrderDaoImpl;
import by.v.ch.dao.impl.PurposeDaoImpl;
import by.v.ch.dao.impl.UserDaoImplementation;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DaoFactory {
    static Logger logger = LoggerFactory.getLogger(DaoFactory.class);
    private static final DaoFactory instance = new DaoFactory();

    //todo: make dynamic?
    private static final PurposeDao purposeDao = new PurposeDaoImpl();
    private static final UserDao userDao = new UserDaoImplementation();
    private static final ClientDao clientDao = new ClientDaoImpl();
    private static final OrderDao orderDao = new OrderDaoImpl();
    //todo:add other dao's

    public ClientDao getClientDao() {
        return clientDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public PurposeDao getPurposeDao()
    {

        logger.info("getting purposeDao");
        return purposeDao;
    }

}
