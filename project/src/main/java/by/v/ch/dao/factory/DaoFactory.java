package by.v.ch.dao.factory;

import by.v.ch.dao.*;
import by.v.ch.dao.impl.*;
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
    private static final DriverDao driverDao = new DriverDaoImpl();
    private static final DispatcherDao dispatcherDao = new DispatcherDaoImpl();
    //todo:add other dao's


    public DispatcherDao getDispatcherDao() {
        return dispatcherDao;
    }

    public DriverDao getDriverDao() { return driverDao; }

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
