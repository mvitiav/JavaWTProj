package by.v.ch.dao.connection;

import by.v.ch.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//todo rename to pool?
public class ConnectionFactory {
    static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    private static final ConnectionFactory instance = new ConnectionFactory();
    int max_connections=5;

    public ConnectionFactory() {
        logger.info("connectionFactory creation started");
       // this.max_connections = max_connections;
        logger.info("call of init started");

        logger.info("call of init stopped");
    }


    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    public static ConnectionFactory getInstance(){
        return instance;
    }

    //todo: add throws exception
    public Connection getConnection()
    {   Connection connection = null;

        try {//todo fix
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void init(){
        logger.info("entered init");
        String driver="com.mysql.cj.jdbc.Driver";
        try {
            logger.info("loading fdriver");
            Class.forName(driver).newInstance();
            logger.info("loaded? driver fdriver");

            connectionQueue=new ArrayBlockingQueue<Connection>(max_connections);
            givenAwayConQueue=new ArrayBlockingQueue<Connection>(max_connections);

//            String url ="jdbc:mysql://192.168.191.128:3306/";
            String url ="jdbc:mysql://192.168.191.128:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//            String user="root";
            String user="admin";
            String password="toor";

            logger.info("generating "+max_connections+" connections");
            for (int i = 0; i < max_connections; i++) {
                logger.info("generating ["+i+"] connection:");
                Connection connection = DriverManager.getConnection(url, user, password);
                logger.info("got connection");
                connection.setAutoCommit(true);
                logger.info("setAutoCommit set");
                connectionQueue.add(connection);
                logger.info("connectionQueue.add(connection) called");
                logger.info("generated ["+i+"] connection");
            }
            logger.info("generated "+max_connections+" connections");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void returnConnection(Connection connection) {
        if (givenAwayConQueue.remove(connection))
            connectionQueue.add(connection);
    }

    //todo:refactor
    public void dispose() {
        try {
            closeConnectionQueue(givenAwayConQueue);
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            //log
        }
    }

    //todo:refactor
    private void closeConnectionQueue(Queue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit())
                connection.commit();
            connection.close();
        }
    }

}
