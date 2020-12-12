package by.v.ch.dao.impl;

import by.v.ch.bean.CarPurpose;
import by.v.ch.dao.PurposeDao;
import by.v.ch.dao.connection.ConnectionFactory;
import by.v.ch.dao.factory.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurposeDaoImpl implements PurposeDao {
    static Logger logger = LoggerFactory.getLogger(PurposeDaoImpl.class);
    //todo rename to pool?
    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_ID = "purpose_id";
    private static final String DB_COLUMN_NAME = "purpose_name";

    //todo move to special file
    //todo: make ame of db dynamic
    private static final String GET_PURPOSE_BY_ID_SQL = "SELECT * FROM new_schema.purpose WHERE new_schema.purpose.purpose_id = ?";

    @Override
    public CarPurpose getById(int id) {
        logger.info("proessing getById request: "+id);
        //todo add exceprion if not ofund
        CarPurpose purpose=null;
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;

        try {
            connection= connectionFactory.getConnection();

            statement = connection.prepareStatement(GET_PURPOSE_BY_ID_SQL);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet == null)
                //todo:throw exception?
                return null;

            //List<Question> questions = new ArrayList<Question>();
         //   while (resultSet.next()) {
            //if(resultSet.)

            if(resultSet.next()) {
                purpose = new CarPurpose(resultSet.getInt(DB_COLUMN_ID), resultSet.getString(DB_COLUMN_NAME));
            }
            else
                {
                    purpose= null;
                    //todo throw exception or log something
                }

          //  }
            statement.close();
            resultSet.close();
            //return purpose;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }
        return purpose;
        //return null;
    }
}
