package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.UserDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class UserDatabaseDao extends MySqlConnection implements UserDao {

    @Override
    public long getUserIdByName(String username) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String sqlQuery = "SELECT `id` FROM `users`  WHERE `username` = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getLong("id");
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
                statement.setString(2, password);
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
            String sqlQuery = "SELECT roles.role FROM roles INNER JOIN users_roles WHERE roles.id = users_roles.role_id AND users_roles.user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, user.getId());
                try(ResultSet resultSet = statement.executeQuery()) {
                    Set<Role> roles = new HashSet<>();
                    while (resultSet.next()) {
                        roles.add(new Role(resultSet.getString("role")));
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
        long id = 0;
        Connection connection = getConnection();
        try{
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String sqlQuery = "INSERT INTO `users` (`username`, `password`) VALUES (?, ?)";
            try(PreparedStatement statementUsers = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statementUsers.setString(1, user.getUsername());
                statementUsers.setString(2, user.getPassword());
                statementUsers.executeUpdate();
                try (ResultSet resultSet = statementUsers.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                    }
                }
            }
            sqlQuery = "INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (?, ?)";
            try(PreparedStatement statementUsersRoles = connection.prepareStatement(sqlQuery)) {
                statementUsersRoles.setLong(1, id);
                statementUsersRoles.setLong(2, new RoleDatabaseDao().getIdRoleByName("user"));
                statementUsersRoles.executeUpdate();
            }
            commitConnection(connection);
            user.setRoles(getUserRoles(user));
        } catch (SQLException e) {
            rollbackConnection(connection);
            throw new ExceptionDao("",e);
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public User getById(long id) {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `users` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
                }
            }
        } catch (SQLException e){
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `users`";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getLong("id"),
                                       resultSet.getString("username"),
                                       resultSet.getString("password")));
                }
                return users;
            }
        } catch (SQLException e){
            throw new ExceptionDao("", e);
        }
    }
}
