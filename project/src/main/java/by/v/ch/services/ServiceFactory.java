package by.v.ch.services;

import by.v.ch.command.commands.ShowInfoCommand;
import by.v.ch.services.impl.OrderServiceImpl;
import by.v.ch.services.impl.TruckServiceImpl;
import by.v.ch.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class ServiceFactory {
    static Logger logger = LoggerFactory.getLogger(ServiceFactory.class);
    //todo:make dynamic
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance(){
        return instance;
    }
    private final UserService userService = new UserServiceImpl();
    private final TruckService truckService = new TruckServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public TruckService getTruckService() {
        logger.info("getting truckservice");
        return truckService; }

    //    private HashSet<myService> implementations;
//
//    public ServiceFactory() {
//        this.implementations = new HashSet<>();
//        implementations.add(new UserServiceImpl());
//    }
//
//    public myService getService(Class c) {
//        if(implementations.contains()){
//
//        }else {
//            return null;
//        }
//
//    }
}
