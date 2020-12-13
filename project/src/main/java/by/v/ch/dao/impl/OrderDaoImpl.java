package by.v.ch.dao.impl;

import by.v.ch.bean.CarPurpose;
import by.v.ch.bean.Client;
import by.v.ch.bean.Order;
import by.v.ch.dao.OrderDao;
import by.v.ch.dao.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_ID = "idorders";
    private static final String DB_COLUMN_CLIENT_ID = "client_id";
    private static final String DB_COLUMN_SIZE = "size";
    private static final String DB_COLUMN_VOLUME = "volume";
    private static final String DB_COLUMN_WEIGHT = "weight";
    private static final String DB_COLUMN_SHIPMENT_DATE = "shipment_date";
    private static final String DB_COLUMN_DESTINATION_DATE = "destination_date";
    private static final String DB_COLUMN_SHIPMENT_POINT = "shipment_point";
    private static final String DB_COLUMN_DESTINATION_POINT = "destination_point";
    private static final String DB_COLUMN_FINISHED = "finished";
    private static final String DB_COLUMN_PRICE = "price";

    private static final String GET_ORDER_BY_ID_SQL = "SELECT * FROM new_schema.orders WHERE new_schema.orders.idorders = ?";
    private static final String ADD_ORDER_SQL = "INSERT INTO new_schema.orders (`client_id`, `size`, `volume`, `weight`, `shipment_date`, `destination_date`, `shipment_point`, `destination_point`) VALUES (?,?,?,?,?,?,?,?);";
    private static final String GET_ORDERS_OF_USER_SQL = "SELECT * FROM new_schema.orders WHERE new_schema.orders.client_id = ?";
    private static final String GET_UNSET_ORDERS = "SELECT * FROM new_schema.orders WHERE new_schema.orders.finished = 0";

    @Override
    public Order getOrderById(int id) {
        logger.info("processing getById request: "+id);
        //todo add exceprion if not ofund
        Order order=null;
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet == null){
                order =null;
            }

            if(resultSet.next()) {
                order = new Order(resultSet.getInt(DB_COLUMN_ID), resultSet.getInt(DB_COLUMN_CLIENT_ID));
                if(resultSet.findColumn(DB_COLUMN_SIZE)!=0){order.setSize(resultSet.getFloat(DB_COLUMN_SIZE));}
                if(resultSet.findColumn(DB_COLUMN_VOLUME)!=0){order.setVolume(resultSet.getFloat(DB_COLUMN_VOLUME));}
                if(resultSet.findColumn(DB_COLUMN_WEIGHT)!=0){order.setWeight(resultSet.getFloat(DB_COLUMN_WEIGHT));}
                if(resultSet.findColumn(DB_COLUMN_SHIPMENT_DATE)!=0){order.setShipmentDate(resultSet.getDate(DB_COLUMN_SHIPMENT_DATE));}
                if(resultSet.findColumn(DB_COLUMN_DESTINATION_DATE)!=0){order.setDestinationDate(resultSet.getDate(DB_COLUMN_DESTINATION_DATE));}
                if(resultSet.findColumn(DB_COLUMN_SHIPMENT_POINT)!=0){order.setShipmentPoint(resultSet.getString(DB_COLUMN_SHIPMENT_POINT));}
                if(resultSet.findColumn(DB_COLUMN_DESTINATION_POINT)!=0){order.setDestinationPoint(resultSet.getString(DB_COLUMN_DESTINATION_POINT));}
                if(resultSet.findColumn(DB_COLUMN_FINISHED)!=0){order.setFinished(resultSet.getBoolean(DB_COLUMN_FINISHED));}
                if(resultSet.findColumn(DB_COLUMN_PRICE)!=0){order.setPrice(resultSet.getFloat(DB_COLUMN_PRICE));}
            }
            else
            {
                order= null;
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
        return order;
    }

    @Override
    public boolean addOrder(Order order) {
        logger.info("adding order : "+order);
        boolean ret=false;
        PreparedStatement statement = null;
        Connection connection=null;
        try {
            connection= connectionFactory.getConnection();
            statement = connection.prepareStatement(ADD_ORDER_SQL);

            logger.info("statement prepared");

            statement.setInt(1, order.getClientId());
            statement.setFloat(2, order.getSize());
            statement.setFloat(3, order.getVolume());
            statement.setFloat(4,  order.getWeight());
            statement.setDate(5, new java.sql.Date(order.getShipmentDate().getTime()));
            statement.setDate(6, new java.sql.Date(order.getDestinationDate().getTime()));
            statement.setString(7, order.getShipmentPoint());
            statement.setString(8, order.getDestinationPoint());


            logger.info("statement set");
            int res = statement.executeUpdate();
            logger.info("statement executed");


            //  }
            statement.close();
            ret=true;
            //return purpose;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //todo: check if null?
            connectionFactory.returnConnection(connection);
        }

        return ret;
    }

    @Override
    public boolean updateOrder(Order order) {

        return false;
    }

    @Override
    public Order[] getOrdersOfClient(Client client) {
        //todo: add using of maxcount
        ArrayList<Order> orders = new ArrayList<Order>();
        //todo add exceprion if not ofund
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;

        try {
            connection= connectionFactory.getConnection();
            logger.info("connection got ");

            statement = connection.prepareStatement(GET_ORDERS_OF_USER_SQL);
            logger.info("statement prepared ");

            statement.setInt(1, client.getClientId());

            resultSet = statement.executeQuery();
            logger.info("statement executed ");
            if (resultSet == null) {
                //todo:throw exception?
                orders = null;
                logger.info("resultSet = null ");
            }else {
                logger.info("resultSet != null");
                while (resultSet.next()) {
                    Order order = new Order(resultSet.getInt(DB_COLUMN_ID), resultSet.getInt(DB_COLUMN_CLIENT_ID));
                    if(resultSet.findColumn(DB_COLUMN_SIZE)!=0){order.setSize(resultSet.getFloat(DB_COLUMN_SIZE));}
                    if(resultSet.findColumn(DB_COLUMN_VOLUME)!=0){order.setVolume(resultSet.getFloat(DB_COLUMN_VOLUME));}
                    if(resultSet.findColumn(DB_COLUMN_WEIGHT)!=0){order.setWeight(resultSet.getFloat(DB_COLUMN_WEIGHT));}
                    if(resultSet.findColumn(DB_COLUMN_SHIPMENT_DATE)!=0){order.setShipmentDate(resultSet.getDate(DB_COLUMN_SHIPMENT_DATE));}
                    if(resultSet.findColumn(DB_COLUMN_DESTINATION_DATE)!=0){order.setDestinationDate(resultSet.getDate(DB_COLUMN_DESTINATION_DATE));}
                    if(resultSet.findColumn(DB_COLUMN_SHIPMENT_POINT)!=0){order.setShipmentPoint(resultSet.getString(DB_COLUMN_SHIPMENT_POINT));}
                    if(resultSet.findColumn(DB_COLUMN_DESTINATION_POINT)!=0){order.setDestinationPoint(resultSet.getString(DB_COLUMN_DESTINATION_POINT));}
                    if(resultSet.findColumn(DB_COLUMN_FINISHED)!=0){order.setFinished(resultSet.getBoolean(DB_COLUMN_FINISHED));}
                    if(resultSet.findColumn(DB_COLUMN_PRICE)!=0){order.setPrice(resultSet.getFloat(DB_COLUMN_PRICE));}
                    orders.add(order);
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

        if(orders!=null) {
            Order[] arr = new Order[orders.size()];
            return orders.toArray(arr);
        }else {return null;}
    }

    @Override
    public Order[] getUnsetOrders() {
        //todo: add using of maxcount
        ArrayList<Order> orders = new ArrayList<Order>();
        //todo add exceprion if not ofund
        PreparedStatement statement = null;
        Connection connection=null;
        ResultSet resultSet = null;

        try {
            connection= connectionFactory.getConnection();
            logger.info("connection got ");

            statement = connection.prepareStatement(GET_UNSET_ORDERS);
            logger.info("statement prepared ");

            resultSet = statement.executeQuery();
            logger.info("statement executed ");
            if (resultSet == null) {
                //todo:throw exception?
                orders = null;
                logger.info("resultSet = null ");
            }else {
                logger.info("resultSet != null");
                while (resultSet.next()) {
                    Order order = new Order(resultSet.getInt(DB_COLUMN_ID), resultSet.getInt(DB_COLUMN_CLIENT_ID));
                    if(resultSet.findColumn(DB_COLUMN_SIZE)!=0){order.setSize(resultSet.getFloat(DB_COLUMN_SIZE));}
                    if(resultSet.findColumn(DB_COLUMN_VOLUME)!=0){order.setVolume(resultSet.getFloat(DB_COLUMN_VOLUME));}
                    if(resultSet.findColumn(DB_COLUMN_WEIGHT)!=0){order.setWeight(resultSet.getFloat(DB_COLUMN_WEIGHT));}
                    if(resultSet.findColumn(DB_COLUMN_SHIPMENT_DATE)!=0){order.setShipmentDate(resultSet.getDate(DB_COLUMN_SHIPMENT_DATE));}
                    if(resultSet.findColumn(DB_COLUMN_DESTINATION_DATE)!=0){order.setDestinationDate(resultSet.getDate(DB_COLUMN_DESTINATION_DATE));}
                    if(resultSet.findColumn(DB_COLUMN_SHIPMENT_POINT)!=0){order.setShipmentPoint(resultSet.getString(DB_COLUMN_SHIPMENT_POINT));}
                    if(resultSet.findColumn(DB_COLUMN_DESTINATION_POINT)!=0){order.setDestinationPoint(resultSet.getString(DB_COLUMN_DESTINATION_POINT));}
                    if(resultSet.findColumn(DB_COLUMN_FINISHED)!=0){order.setFinished(resultSet.getBoolean(DB_COLUMN_FINISHED));}
                    if(resultSet.findColumn(DB_COLUMN_PRICE)!=0){order.setPrice(resultSet.getFloat(DB_COLUMN_PRICE));}
                    orders.add(order);
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

        if(orders!=null) {
            Order[] arr = new Order[orders.size()];
            return orders.toArray(arr);
        }else {return null;}
    }
}
