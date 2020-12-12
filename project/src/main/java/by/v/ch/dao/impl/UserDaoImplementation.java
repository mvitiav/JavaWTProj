package by.v.ch.dao.impl;

import by.v.ch.bean.User;
import by.v.ch.dao.UserDao;
import by.v.ch.dao.connection.ConnectionFactory;
import by.v.ch.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserDaoImplementation implements UserDao {
    static Logger logger = LoggerFactory.getLogger(UserDaoImplementation.class);
    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private static final String DB_COLUMN_PASS_HASH = "passHash";
    private static final String DB_COLUMN_NAME = "username";
    private static final String DB_COLUMN_SALT = "salt";
    private static final String DB_COLUMN_ID = "id_users";
    private static final String DB_COLUMN_ROLE = "role_id";

    //todo move to special file
    //todo: make ame of db dynamic
    private static final String GET_PURPOSE_BY_ID_SQL = "SELECT * FROM new_schema.purpose WHERE new_schema.purpose.purpose_id = ?";
    private static final String INSERT_NEW_USER_SQL = "INSERT INTO new_schema.users (`username`, `passHash`, `salt`) VALUES (?,?,?);";
    private static final String LOG_IN_SQL = "SELECT * FROM new_schema.users WHERE new_schema.users.username = ?;";

    private static String generateSalt(){
        byte[] array = new byte[45];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.US_ASCII);
    }

    //todo add self-check
    private static String countHash(String pass,String salt){

        String hash="";
        try {
            String input = pass+salt;
            logger.info("input = "+input);
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
            logger.info("instance got");
        md.update(input.getBytes());
            logger.info("md updated");
            StringBuffer hexString = new StringBuffer();
            byte[] digest = md.digest();
            logger.info("converting digest");
            for (int i = 0; i < digest.length; i++) {
                if ((0xff & digest[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & digest[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & digest[i]));
                }
            }
            logger.info("converted digest");
            hash=hexString.toString();
//            .substring(0,45);
            logger.info("truncating string");
            if(hash.length()>45){
                hash=hash.substring(0,45);
            }
            logger.info("hash = "+hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    @Override
    public boolean register(String name, String pass) {
        logger.info("register called");
        boolean ret=false;
        Connection connection=null;
        PreparedStatement statement = null;
        try {
            logger.info("getting connection");
            connection=ConnectionFactory.getInstance().getConnection();
            logger.info("connection got");
            statement=connection.prepareStatement(INSERT_NEW_USER_SQL);
            logger.info("Statement prepared");
            String salt=generateSalt();
            logger.info("salt generated");
            String hash=countHash(pass,salt);
            logger.info("salt and hash generated");
            statement.setString(1,name);
            statement.setString(2,hash);
            statement.setString(3,salt);

            logger.info("executing statement");
            int res=statement.executeUpdate();
            logger.info("statement executed");

            statement.close();

            //todo: check
            ret=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.getInstance().returnConnection(connection);
        }

        return ret;
    }

    @Override
    public User logIn(String name, String pass) {
        logger.info("login called");
        User ret = null;

        Connection connection=null;
        PreparedStatement statement = null;
        try {
            logger.info("getting connection");
            connection=ConnectionFactory.getInstance().getConnection();
            logger.info("connection got");
            statement=connection.prepareStatement(LOG_IN_SQL);
            logger.info("Statement prepared");

            statement.setString(1,name);

//            statement.setString(2,hash);
//            statement.setString(3,salt);

            //todo: changet oexecQuerry?
            logger.info("executing statement");
            ResultSet resultSet =statement.executeQuery();
            logger.info("statement executed");

            if(resultSet.next()){
                logger.info("user found");

                if(
                        resultSet.getString(DB_COLUMN_PASS_HASH)
                                .compareTo(
                                        countHash(pass,resultSet.getString(DB_COLUMN_SALT))
                                )==0

                ){
                  logger.info("success");
                  ret = new User(resultSet.getInt(DB_COLUMN_ID),resultSet.getString(DB_COLUMN_NAME));
                  logger.info("role is "+resultSet.getInt(DB_COLUMN_ROLE));
                  ret.setRoleFromInt(resultSet.getInt(DB_COLUMN_ROLE));
                }else {
                    logger.info("wrong pass");
                }
            }else {
                logger.info("user not found");
                ret=null;

                //todo: throw exception about not finding anything
            }
            resultSet.close();
            statement.close();

            //todo: check

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.getInstance().returnConnection(connection);
        }

        return ret;
    }
}
