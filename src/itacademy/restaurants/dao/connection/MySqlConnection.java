package itacademy.restaurants.dao.connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import itacademy.restaurants.dao.ExceptionDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by aVa on 10.01.2017.
 */
public abstract class MySqlConnection {

    private static String propertyFilePath = "d:\\11.Course\\restaurants\\restaurants\\src\\itacademy\\resources\\database.properties";
    //private static String propertyFilePath = "e:\\Java\\restaurants\\restaurants\\src\\itacademy\\resources\\database.properties";

    private static final DataSource dataSource = init();
    private static String driverClassName;
    private static String username;
    private static String password;
    private static String url;

    private static DataSource init() throws ExceptionDao {
        readConfig();
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setTestOnConnect(true);
        poolProperties.setMaxActive(100);
        poolProperties.setMaxIdle(30);
        poolProperties.setMaxWait(1000);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setRemoveAbandonedTimeout(30);
        poolProperties.setLogAbandoned(true);
        poolProperties.setUrl(url);
        poolProperties.setUsername(username);
        poolProperties.setPassword(password);
        poolProperties.setDriverClassName(driverClassName);
        return new DataSource(poolProperties);
    }

    protected static Connection getConnection() throws NullPointerException, ExceptionDao {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    public static void closeConnection(Connection connection) throws ExceptionDao {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }

    public static void commitConnection(Connection connection) throws ExceptionDao {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }

    public static void rollbackConnection(Connection connection) throws ExceptionDao {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }


    private static void readConfig() throws ExceptionDao {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(propertyFilePath)));
            driverClassName = props.getProperty("driverClassName");
            username = props.getProperty("username");
            password = props.getProperty("password");
            url = String.format("%s:%s://%s:%d/%s", props.getProperty("protocol"),
                                                    props.getProperty("subprotocol"),
                                                    props.getProperty("host"),
                                                    Integer.valueOf(props.getProperty("port")),
                                                    props.getProperty("dbName"));
        } catch (IOException e) {
            throw new ExceptionDao("", e);
        }
    }


}

