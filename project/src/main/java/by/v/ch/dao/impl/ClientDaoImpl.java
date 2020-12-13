package by.v.ch.dao.impl;

import by.v.ch.bean.Client;
import by.v.ch.bean.Order;
import by.v.ch.dao.ClientDao;
import by.v.ch.dao.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements ClientDao {

    static Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_ID = "idclients";
    private static final String DB_COLUMN_USER_ID = "user_id";

    private static final String GET_CLIENT_BY_ID_SQL = "SELECT * FROM new_schema.clients WHERE new_schema.clients.user_id = ?";
    private static final String ADD_CLIENT_SQL = "INSERT INTO new_schema.clients (`user_id`) VALUES (?);";

    @Override
    public Client getById(int user_id) {
        logger.info("processing getById request: "+user_id);
        //todo add exceprion if not ofund
        Client client=null;
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_ID_SQL);
            statement.setInt(1, user_id);
            resultSet = statement.executeQuery();
            if (resultSet == null){
                client =null;
            }

            if(resultSet.next()) {
                client = new Client(resultSet.getInt(DB_COLUMN_ID), resultSet.getInt(DB_COLUMN_USER_ID));
            }
            else
            {
                client= null;
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
        return client;
    }

    @Override
    public boolean addNew(int user_id) {
        boolean ret=false;
        PreparedStatement statement = null;
        Connection connection=null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(ADD_CLIENT_SQL);

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
