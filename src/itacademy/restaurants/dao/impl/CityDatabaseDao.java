package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CityDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.City;
import java.sql.*;
import java.util.List;
import java.util.Set;


public class CityDatabaseDao implements CityDao {

    private MySQL connections;

    public CityDatabaseDao() {
        connections = MySQL.CONNECTIONS;
    }

    @Override
    public long add(City city) throws ExceptionDao {
        try(Connection connection = connections.getConnection()) {
            String strSql = "INSERT INTO `cities` (`name`, `country_id`) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(strSql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, city.getName());
                statement.setLong(2, city.getCountry().getId());
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
    public boolean update(City city) throws ExceptionDao {
        return false;
    }

    @Override
    public boolean remove(City city) throws ExceptionDao {
        try(Connection connection = connections.getConnection()){
            String strSql = "DELETE FROM `cities` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, city.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public City getById(long id) throws ExceptionDao {
        try(Connection connection = connections.getConnection()) {
            String strSql = "SELECT * FROM `cities` WHERE `id` = ?";
            try(PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        //return new City(resultSet.getLong("id"), resultSet.getString("name"), countryDatabaseDao.getById(resultSet.getLong("country_id")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<City> getAll() throws ExceptionDao {
        return null;
    }

    @Override
    public City getCityByName(String name) throws ExceptionDao {
        return null;
    }

    @Override
    public List<City> getCitiesByCountry(Country country) throws ExceptionDao {
        return null;
    }
}
