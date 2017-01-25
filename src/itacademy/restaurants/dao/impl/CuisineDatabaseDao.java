package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.dao.CuisineDao;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class CuisineDatabaseDao implements CuisineDao {

    private MySQL connections;

    public CuisineDatabaseDao() {
        connections = MySQL.CONNECTIONS;
    }

    @Override
    public Cuisine getCuisineByName(String name) {
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT * FROM `cuisines` WHERE `name` LIKE ?;";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, name);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Cuisine(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public long add(Cuisine cuisine) {
        try(Connection connection = connections.getConnection()) {
            String strSql = "INSERT INTO `cuisines` (`name`) VALUES (?);";
            try(PreparedStatement statement = connection.prepareStatement(strSql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, cuisine.getName());
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
    public boolean update(Cuisine cuisine) {
        return false;
    }

    @Override
    public boolean remove(Cuisine cuisine) {
        try(Connection connection = connections.getConnection()){
            String strSql = "DELETE FROM `cuisines` WHERE `id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, cuisine.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public Cuisine getById(long id) {
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT * FROM `cuisines` WHERE `id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Cuisine(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<Cuisine> getAll() {
        Set<Cuisine> cuisines = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String strSql = ("SELECT * FROM `cuisines` ORDER BY `id` ASC;");
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cuisines.add(new Cuisine(resultSet.getLong("id"), resultSet.getString("name")));
                    }
                    return cuisines;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
