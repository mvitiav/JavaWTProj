package by.v.ch.dao.impl;

import by.v.ch.bean.Dispatcher;
import by.v.ch.bean.Driver;
import by.v.ch.bean.Order;
import by.v.ch.bean.User;
import by.v.ch.dao.DriverDao;
import by.v.ch.dao.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDaoImpl implements DriverDao {

    static Logger logger = LoggerFactory.getLogger(DriverDaoImpl.class);

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static final String DB_COLUMN_ID = "iddrivers";
    private static final String DB_COLUMN_USER_ID = "id_of_user";
    private static final String DB_COLUMN_WAGE = "driver_wage";
    private static final String DB_COLUMN_CAR_ID = "car_id";
    private static final String DB_COLUMN_DISPATCHER_ID = "disp_id";


    private static final String GET_DRIVER_BY_ID_SQL = "SELECT * FROM new_schema.drivers WHERE new_schema.drivers.user_id = ?";
    private static final String ADD_DRIVER_SQL = "INSERT INTO new_schema.drivers (`id_of_user`) VALUES (?);";
    private static final String GET_DRIVERS_OF_DISPATCHER = "SELECT * FROM new_schema.drivers WHERE new_schema.drivers.disp_id = ?";

    @Override
    public Driver getDriverById(int user_id) {
        logger.info("processing getById request: "+user_id);
        //todo add exceprion if not ofund
        Driver driver=null;
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(GET_DRIVER_BY_ID_SQL);
            statement.setInt(1, user_id);
            resultSet = statement.executeQuery();
            if (resultSet == null){
                driver =null;
            }

            if(resultSet.next()) {
                driver = new Driver(resultSet.getInt(DB_COLUMN_ID),
                        resultSet.getInt(DB_COLUMN_USER_ID),
                        resultSet.getInt(DB_COLUMN_DISPATCHER_ID),
                        resultSet.getInt(DB_COLUMN_WAGE),
                        resultSet.getInt(DB_COLUMN_CAR_ID)
                );
            }
            else
            {
                driver= null;
                //todo throw exception or log something
            }

            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }
        return driver;
    }

    @Override
    public boolean addDriver(User user) {
        boolean ret=false;
        PreparedStatement statement = null;
        Connection connection=null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(ADD_DRIVER_SQL);
            statement.setInt(1,user.getId());
            int res = statement.executeUpdate();
            statement.close();
            ret=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }
        return ret;
    }

    @Override
    public Driver[] getDriversOfDispatcher(Dispatcher dispatcher) {
        //todo: add using of maxcount
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        //todo add exceprion if not ofund
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;

        try {
            connection= connectionFactory.getConnection();
            logger.info("connection got ");

            statement = connection.prepareStatement(GET_DRIVERS_OF_DISPATCHER);
            logger.info("statement prepared ");

            statement.setInt(1,dispatcher.getDispatcherId());

            resultSet = statement.executeQuery();
            logger.info("statement executed ");
            if (resultSet == null) {
                //todo:throw exception?
                drivers = null;
                logger.info("resultSet = null ");
            }else {
                logger.info("resultSet != null");
                while (resultSet.next()) {
                    Driver driver = new Driver(resultSet.getInt(DB_COLUMN_ID),
                            resultSet.getInt(DB_COLUMN_USER_ID),
                            resultSet.getInt(DB_COLUMN_DISPATCHER_ID),
                            resultSet.getInt(DB_COLUMN_WAGE),
                            resultSet.getInt(DB_COLUMN_CAR_ID));
                    drivers.add(driver);
                }
            }

            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }

        if(drivers!=null) {
            Driver[] arr = new Driver[drivers.size()];
            return drivers.toArray(arr);
        }else {return null;}
    }
}
