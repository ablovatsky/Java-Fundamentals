package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RoleDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */
public class RoleDatabaseDao implements RoleDao {


    @Override
    public Long getIdRoleByName(String name) throws ExceptionDao {
        try(Connection connection = new MySqlConnection().getConnection()) {
            String strSql = "SELECT `id` FROM `roles` WHERE `role` LIKE ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
           throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public void add(Role model) throws ExceptionDao, SQLException {

    }

    @Override
    public void update(Role model) {

    }

    @Override
    public void remove(Role model) {

    }

    @Override
    public Role getById() {
        return null;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }
}
