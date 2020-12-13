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
import java.util.ArrayList;

public class PurposeDaoImpl implements PurposeDao {
    static Logger logger = LoggerFactory.getLogger(PurposeDaoImpl.class);
    //todo rename to pool?
    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_ID = "purpose_id";
    private static final String DB_COLUMN_NAME = "purpose_name";

    //todo move to special file
    //todo: make ame of db dynamic
    private static final String GET_PURPOSE_BY_ID_SQL = "SELECT * FROM new_schema.purpose WHERE new_schema.purpose.purpose_id = ?";
    private static final String GET_PURPOSES_SQL = "SELECT * FROM new_schema.purpose";

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
                purpose= null;

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

    @Override
    public CarPurpose[] getAllPurposes() {
        logger.info("proessing getAllPurposes request: ");
        ArrayList<CarPurpose> carPurposes = new ArrayList<CarPurpose>();
        //todo add exceprion if not ofund
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;

        try {
            connection= connectionFactory.getConnection();
            logger.info("connection got ");

            statement = connection.prepareStatement(GET_PURPOSES_SQL);
            logger.info("statement prepared ");

            resultSet = statement.executeQuery();
            logger.info("statement executed ");
            if (resultSet == null) {
                //todo:throw exception?
                carPurposes = null;
                logger.info("resultSet = null ");
            }else {
                logger.info("resultSet != null");
                while (resultSet.next()) {
                    carPurposes.add(new CarPurpose(resultSet.getInt(DB_COLUMN_ID), resultSet.getString(DB_COLUMN_NAME)));
                    logger.info(resultSet.getString(DB_COLUMN_NAME));
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

        if(carPurposes!=null) {
            CarPurpose[] arr = new CarPurpose[carPurposes.size()];
            return carPurposes.toArray(arr);
        }else {return null;}

    }
}
