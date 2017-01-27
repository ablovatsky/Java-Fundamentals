package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CountryDao;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class CountryDatabaseDao implements CountryDao {

    private MySQL connections;

    public CountryDatabaseDao() {
        connections = MySQL.CONNECTIONS;
    }

    @Override
    public Country getCountryByName(String name) throws ExceptionDao {
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT * FROM `countries` WHERE `name` LIKE ?;";
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
        try(Connection connection = connections.getConnection()) {
            String strSql = "INSERT INTO `countries` (`name`) VALUES (?);";
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
        try(Connection connection = connections.getConnection()){
            String strSql = "DELETE FROM `countries` WHERE `id` = ?;";
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
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT * FROM `countries` WHERE `id` = ?;";
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
        Set<Country> countries = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT t1.name AS countryName, t1.id AS countryId, t2.name AS cityName, t2.id AS cityId " +
                                "FROM countries t1 " +
                                "INNER JOIN cities t2 ON t1.id = t2.country_id " +
                                "ORDER BY t1.name ASC ;";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    String preCountryName = "";
                    Country country = new Country();
                    Set<City> cities = new LinkedHashSet<>();
                    while (resultSet.next()) {
                        String countryName = resultSet.getString("countryName");
                        if (!preCountryName.equals(countryName)) {
                            if (!preCountryName.equals("")) {
                                country.setCities(cities);
                                countries.add(country);
                            }
                            country = new Country(resultSet.getLong("countryId"), countryName);
                            cities = new LinkedHashSet<>();
                            cities.add(new City(resultSet.getLong("cityId"), resultSet.getString("cityName")));
                        } else {
                            cities.add(new City(resultSet.getLong("cityId"), resultSet.getString("cityName")));
                        }
                        preCountryName = resultSet.getString("countryName");
                    }
                    return countries;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
