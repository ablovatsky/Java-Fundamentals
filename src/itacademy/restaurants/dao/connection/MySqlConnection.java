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

    private String driverClassName;
    private String username;
    private String password;
    private String url;

    public Connection getConnection() throws NullPointerException, ExceptionDao {
        try {
            readConfig();
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ExceptionDao("",e);
        }
    }

    private void readConfig() throws ExceptionDao {
        Properties props = new Properties();

        String propertyFile = "e:\\Java\\restaurants\\src\\itacademy\\resources\\database.properties";

        String protocol, subprotocol, host, dbName;
        int port;
        try {
            props.load(new FileInputStream(new File(propertyFile)));
        } catch (IOException e) {
            throw new ExceptionDao("", e);
        }
        driverClassName = props.getProperty("driverClassName");
        username = props.getProperty("username");
        password = props.getProperty("password");
        protocol = props.getProperty("protocol");
        subprotocol = props.getProperty("subprotocol");
        host = props.getProperty("host");
        port = Integer.valueOf(props.getProperty("port"));
        dbName = props.getProperty("dbName");
        url = String.format("%s:%s://%s:%d/%s", protocol, subprotocol, host, port, dbName);
    }
}

