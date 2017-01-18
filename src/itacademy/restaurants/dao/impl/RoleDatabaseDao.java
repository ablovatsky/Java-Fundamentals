package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RoleDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Role;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */
public class RoleDatabaseDao extends MySqlConnection implements RoleDao {

    @Override
    public Long getIdRoleByName(String name) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT `id` FROM `roles` WHERE `name` LIKE ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, name);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException e) {
           throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public long add(Role role) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "INSERT INTO `roles` (`name`) VALUES (?)";
            try(PreparedStatement statement = connection.prepareStatement(strSql,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, role.getName());
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return 0;
    }

    @Override
    public boolean update(Role role) throws ExceptionDao {
        return false;
    }

    @Override
    public boolean remove(Role role) throws ExceptionDao {
        try(Connection connection = getConnection()){
            String strSql = "DELETE FROM `roles` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, role.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }


    @Override
    public Role getById(long id) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `roles` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Role(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<Role> getAll() throws ExceptionDao {
        Set<Role> roles = new HashSet<>();
        try(Connection connection = getConnection()) {
            String strSql = ("SELECT * FROM `roles`");
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        roles.add(new Role(resultSet.getLong("id"), resultSet.getString("name")));
                    }
                    return roles;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
