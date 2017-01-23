package itacademy.restaurants.dao.connection;

import itacademy.restaurants.dao.ExceptionDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public enum MySQL {

    CONNECTIONS;

    //private String propertyFilePath = "e:\\Java\\restaurants\\restaurants\\resources\\database\\database.properties";
    private String propertyFilePath = "d:\\11.Course\\restaurants\\restaurants\\resources\\database\\database.properties";


    private DataSource dataSource;
    private String driverClassName;
    private String username;
    private String password;
    private String url;

    private MySQL() {
        init();
    }

    public Connection getConnection() throws NullPointerException, ExceptionDao {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    private void init() throws ExceptionDao {
        readConfig();
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setTestOnConnect(true);
        poolProperties.setMaxActive(100);
        poolProperties.setMaxIdle(30);
        poolProperties.setMaxWait(1000);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setRemoveAbandonedTimeout(30);
        poolProperties.setLogAbandoned(true);
        poolProperties.setTestOnConnect(true);
        poolProperties.setUrl(url);
        poolProperties.setUsername(username);
        poolProperties.setPassword(password);
        poolProperties.setDriverClassName(driverClassName);
        dataSource = new DataSource(poolProperties);
    }

    public void closeConnection(Connection connection) throws ExceptionDao {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }

    public void commitConnection(Connection connection) throws ExceptionDao {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }

    public void rollbackConnection(Connection connection) throws ExceptionDao {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new ExceptionDao("",e);
        }
    }

    private void readConfig() throws ExceptionDao {
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
