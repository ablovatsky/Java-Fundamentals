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
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "";
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
        return null;
    }

    @Override
    public Set<Restaurant> getAll() throws ExceptionDao {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT `id`, `name`, `website`, `short_information`, `image` FROM restaurants ORDER BY `id` ASC";
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
    public Set<Restaurant> getRestaurantsByName(String name) throws ExceptionDao {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT `id`, `name`, `website`, `short_information`, `image` FROM restaurants WHERE LOWER(`name`) LIKE ? ORDER BY `id` ASC";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, name.toLowerCase());
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
    public Set<Restaurant> getRestaurantsByCuisine(String name) {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.id, t1.name, t1.website, t1.short_information, t1.image FROM restaurants t1 INNER JOIN\n" +
                              "(SELECT t1.restaurant_id FROM restaurant_cuisine t1 INNER JOIN cuisines t2 ON t1.cuisine_id = t2.id WHERE t2.name LIKE ?) t2\n" +
                              "ON t1.id = t2.restaurant_id";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, name.toLowerCase());
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
    public Set<Restaurant> getRestaurantsByCity(String name) {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.id, t1.name, t1.website, t1.short_information, t1.image FROM restaurants t1 " +
                              "INNER JOIN " +
                              "(SELECT address.restaurant_id, cities.name FROM address INNER JOIN cities ON address.city_id = cities.id WHERE LOWER(cities.name) LIKE ?) t2 " +
                              "ON t1.id = t2.restaurant_id " +
                              "ORDER BY `id` ASC";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, name.toLowerCase());
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
    public Set<Restaurant> getRestaurantsByCountry(String name) {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.id, t1.name, t1.website, t1.short_information, t1.image FROM restaurants t1 " +
                              "INNER JOIN " +
                              "((SELECT t1.restaurant_id FROM address t1 INNER JOIN " +
                              "(SELECT t1.id FROM cities t1 INNER JOIN countries t2 ON t1.country_id = t2.id WHERE LOWER(t2.name) LIKE ?) t2 " +
                              "ON t1.city_id = t2.id)) t2 " +
                              "ON t1.id = t2.restaurant_id " +
                              "ORDER BY `id` ASC";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, name.toLowerCase());
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
}
