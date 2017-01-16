package itacademy.restaurants.dao.connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import itacademy.restaurants.dao.ExceptionDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by aVa on 10.01.2017.
 */
public class MySqlConnection {

    private static Properties props;

    private static String driverClassName;
    private static String username;
    private static String password;
    private static String url;

    public MySqlConnection() throws ExceptionDao {

    }

    public static Connection getConnection() throws NullPointerException, ExceptionDao {
        init();
        try {

            Class.forName(driverClassName);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ExceptionDao("",e);
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

    private static void init() throws ExceptionDao {
        readConfig();
        driverClassName = props.getProperty("driverClassName");
        username = props.getProperty("username");
        password = props.getProperty("password");
        url = String.format("%s:%s://%s:%d/%s", props.getProperty("protocol"),
                                                props.getProperty("subprotocol"),
                                                props.getProperty("host"),
                                                Integer.valueOf(props.getProperty("port")),
                                                props.getProperty("dbName"));
    }
    private static void readConfig() throws ExceptionDao {
        props = new Properties();
        //String propertyFile = "e:\\Java\\restaurants\\src\\itacademy\\resources\\database.properties";
        String propertyFile = "d:\\11.Course\\restaurants\\restaurants\\src\\itacademy\\resources\\database.properties";

        try {
            props.load(new FileInputStream(new File(propertyFile)));
        } catch (IOException e) {
            throw new ExceptionDao("", e);
        }
    }


}

