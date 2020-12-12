package by.v.ch.services.impl;

import by.v.ch.bean.User;
import by.v.ch.dao.UserDao;
import by.v.ch.dao.factory.DaoFactory;
import by.v.ch.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {
    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User logIn(String username, String password) {
        logger.info("login called");
        DaoFactory daoFactory=DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        logger.info("got userDao");
        return userDao.logIn(username,password);
//        return purposeDao.getById(id);
    }

    @Override
    public boolean register( String username, String password) {
        logger.info("register called");
        DaoFactory daoFactory=DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        logger.info("got userDao");
        return userDao.register(username,password);
//        return purposeDao.getById(id);

    }


}
