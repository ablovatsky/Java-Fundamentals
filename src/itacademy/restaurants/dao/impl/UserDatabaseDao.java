package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.UserDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Model;
import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class UserDatabaseDao implements UserDao {

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) throws ExceptionDao {
        try(Connection connection = MySqlConnection.getConnection()) {
            String sqlQuery = "SELECT * FROM `users`  WHERE `username` = ? AND `password` = ?";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try(ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        User user = new User(result.getLong("id"), result.getString("username"), result.getString("password"));
                        connection.close();
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
        try(Connection connection = MySqlConnection.getConnection()) {
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
    public void add(User user) throws ExceptionDao{
        Connection connection = MySqlConnection.getConnection();
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
                        user.setId(resultSet.getLong(1));
                    }
                }
            }
            sqlQuery = "INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (?, ?)";
            try(PreparedStatement statementUsersRoles = connection.prepareStatement(sqlQuery)) {
                statementUsersRoles.setLong(1, user.getId());
                statementUsersRoles.setLong(2, new RoleDatabaseDao().getIdRoleByName("user"));
                statementUsersRoles.executeUpdate();
            }
            MySqlConnection.commitConnection(connection);
            user.setRoles(getUserRoles(user));
        } catch (SQLException e) {
            MySqlConnection.rollbackConnection(connection);
            throw new ExceptionDao("",e);
        } finally {
            MySqlConnection.closeConnection(connection);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public User getById() {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }
}
