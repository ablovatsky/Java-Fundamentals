package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CountryDao;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Model;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CountryDatabaseDao extends MySqlConnection implements CountryDao {

    @Override
    public Country getCountryByName(String name) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `countries` WHERE `name` LIKE ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, name);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Country(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public long add(Country country) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "INSERT INTO `countries` (`name`) VALUES (?)";
            try(PreparedStatement statement = connection.prepareStatement(strSql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, country.getName());
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
    public boolean update(Country country) throws ExceptionDao {
        return false;
    }

    @Override
    public boolean remove(Country country) throws ExceptionDao {
        try(Connection connection = getConnection()){
            String strSql = "DELETE FROM `countries` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, country.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }


    @Override
    public Country getById(long id) throws ExceptionDao {
        try(Connection connection = getConnection()) {
            String strSql = "SELECT * FROM `countries` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Country(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<Country> getAll() throws ExceptionDao {
        Set<Country> countries = new HashSet<>();
        try(Connection connection = getConnection()) {
            String strSql = ("SELECT * FROM `countries`");
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        countries.add(new Country(resultSet.getLong("id"), resultSet.getString("name")));
                    }
                    return countries;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
