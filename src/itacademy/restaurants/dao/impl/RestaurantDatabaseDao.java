package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class RestaurantDatabaseDao implements RestaurantDao {

    private MySQL connections;

    public RestaurantDatabaseDao() {
        connections = MySQL.CONNECTIONS;
    }

    @Override
    public long add(Restaurant restaurant) throws ExceptionDao {
        long id = 0;
        return id;
    }

    @Override
    public boolean update(Restaurant model) throws ExceptionDao {
        return false;
    }

    @Override
    public boolean remove(Restaurant model) throws ExceptionDao {
        return false;
    }


    @Override
    public Restaurant getById(long id) throws ExceptionDao {
        return null;
    }

    @Override
    public Set<Restaurant> getAll() throws ExceptionDao {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT `id`, `name`, `website`, `working_hours`, `short_information`, `image` FROM restaurants ORDER BY `id` ASC";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Restaurant restaurant = new Restaurant();
                        restaurant.setId(resultSet.getLong("id"));
                        restaurant.setName(resultSet.getString("name"));
                        restaurant.setWebsite(resultSet.getString("website"));
                        restaurant.setShortInformation(resultSet.getString("short_information"));
                        restaurant.setImage(resultSet.getBytes("image"));
                        restaurants.add(restaurant);
                    }
                    return restaurants;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public Restaurant getRestaurantByName(String name) throws ExceptionDao {
        return null;
    }

    @Override
    public Set<Cuisine> getRestaurantCuisines(Restaurant restaurant) throws ExceptionDao {
        return null;
    }

    @Override
    public void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine) throws ExceptionDao {

    }

    @Override
    public void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine) {

    }

    @Override
    public List<Restaurant> getRestaurantsByCuisine(Cuisine cuisine) {
        return null;
    }

    @Override
    public Set<City> getRestaurantAddresses(Restaurant restaurant) {
        return null;
    }

    @Override
    public void addAddressToRestaurant(Restaurant restaurant, City city) {

    }

    @Override
    public void removeAddressFromRestaurant(Restaurant restaurant, City city) {

    }

    @Override
    public List<Restaurant> getRestaurantsByCity(City city) {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurantsByCountry(Country country) {
        return null;
    }
}
