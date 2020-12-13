package by.v.ch.dao.impl;

import by.v.ch.bean.Client;
import by.v.ch.bean.Dispatcher;
import by.v.ch.bean.User;
import by.v.ch.dao.DispatcherDao;
import by.v.ch.dao.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DispatcherDaoImpl implements DispatcherDao {
    static Logger logger = LoggerFactory.getLogger(DispatcherDao.class);

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_ID = "iddispatchers";
    private static final String DB_COLUMN_USER_ID = "userid";
    private static final String DB_COLUMN_MAX_DRIVERS = "max_drivers";

    private static final String GET_DISPATCHER_BY_USER_ID_SQL = "SELECT * FROM new_schema.dispatchers WHERE new_schema.dispatchers.user_id = ?";
    private static final String ADD_DISPATCHER_SQL = "INSERT INTO new_schema.dispatchers (`userid`) VALUES (?);";

    @Override
    public Dispatcher getByUser(User user) {
        int user_id=user.getId();
        logger.info("processing getById request: "+user_id);
        //todo add exceprion if not ofund
        Dispatcher dispatcher=null;
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(GET_DISPATCHER_BY_USER_ID_SQL);
            statement.setInt(1, user_id);
            resultSet = statement.executeQuery();
            if (resultSet == null){
                dispatcher =null;
            }

            if(resultSet.next()) {
                dispatcher = new Dispatcher(resultSet.getInt(DB_COLUMN_ID),resultSet.getInt(DB_COLUMN_USER_ID),resultSet.getInt(DB_COLUMN_USER_ID));
            }
            else
            {
                dispatcher= null;
                //todo throw exception or log something
            }

            statement.close();
            resultSet.close();
            //return purpose;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }
        return dispatcher;
    }

    @Override
    public boolean addNew(User user) {
        int user_id=user.getId();
        boolean ret=false;
        PreparedStatement statement = null;
        Connection connection=null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(ADD_DISPATCHER_SQL);
            statement.setInt(1,user_id);
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
}
