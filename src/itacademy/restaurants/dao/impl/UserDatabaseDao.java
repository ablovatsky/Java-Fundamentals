package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.UserDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class UserDatabaseDao extends MySqlConnection implements UserDao {

    private static String md5Custom(String str) throws ExceptionDao{
        MessageDigest messageDigest;
        byte[] digest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new ExceptionDao("", e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    @Override
    public long getUserIdByName(String username) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String sqlQuery = "SELECT `id` FROM `users`  WHERE `username` = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, username);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return 0;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String sqlQuery = "SELECT * FROM `users`  WHERE `username` = ? AND `password` = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, username);
                statement.setString(2, md5Custom(password));
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
                        user.setRoles(getUserRoles(user));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<Role> getUserRoles(User user) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String sqlQuery = "SELECT roles.name FROM roles INNER JOIN users_roles WHERE roles.id = users_roles.role_id AND users_roles.user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, user.getId());
                try(ResultSet resultSet = statement.executeQuery()) {
                    Set<Role> roles = new HashSet<>();
                    while (resultSet.next()) {
                        roles.add(new Role(resultSet.getString("name")));
                    }
                    return roles;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public long add(User user) throws ExceptionDao{
        long userId = 0;
        long roleId = 0;
        Connection connection = getConnection();
        try{
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String sqlQuery = "INSERT INTO `users` (`username`, `password`) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, md5Custom(user.getPassword()));
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userId = resultSet.getLong(1);
                    }
                }
            }
            sqlQuery = "SELECT `id` FROM `roles` WHERE `name` LIKE 'user'";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try(ResultSet resultSet = statement.executeQuery() ) {
                    if (resultSet.next()) {
                        roleId = resultSet.getLong(1);
                    }
                }
            }
            if (roleId > 0) {
                sqlQuery = "INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                    statement.setLong(1, userId);
                    statement.setLong(2, roleId);
                    statement.executeUpdate();
                }
                commitConnection(connection);
                user.setRoles(getUserRoles(user));
            } else {
                rollbackConnection(connection);
                userId = 0;
            }
        } catch (SQLException e) {
            rollbackConnection(connection);
            throw new ExceptionDao("",e);
        } finally {
            closeConnection(connection);
        }
        return userId;
    }

    @Override
    public boolean update(User user) throws ExceptionDao {
        return false;
    }

    @Override
    public boolean remove(User user) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "DELETE FROM `users` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, user.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public User getById(long id) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `users` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
                    }
                }
            }
        } catch (SQLException e){
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<User> getAll() throws ExceptionDao {
        Set<User> users = new HashSet<>();
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `users`";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(new User(resultSet.getLong("id"),
                                resultSet.getString("username"),
                                resultSet.getString("password")));
                    }
                    return users;
                }
            }
        } catch (SQLException e){
            throw new ExceptionDao("", e);
        }
    }
}
