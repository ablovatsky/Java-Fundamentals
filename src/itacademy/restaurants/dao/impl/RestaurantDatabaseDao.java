package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Restaurant restaurant = new Restaurant();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT * FROM `restaurants` WHERE `id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        restaurant.setId(resultSet.getLong("id"));
                        restaurant.setName(resultSet.getString("name"));
                        restaurant.setPhone(resultSet.getString("phone"));
                        restaurant.setWorkingHours(resultSet.getString("working_hours"));
                        restaurant.setWebsite(resultSet.getString("website"));
                        restaurant.setInformation(resultSet.getString("information"));
                        restaurant.setImage(resultSet.getBytes("image"));
                    }
                }
            }
            sqlQuery = "SELECT t1.id, t1.name " +
                        "FROM cuisines t1 " +
                        "INNER JOIN restaurant_cuisine t2 ON t1.id = t2.cuisine_id " +
                        "WHERE t2.restaurant_id = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    Set<Cuisine> cuisines = new LinkedHashSet<>();
                    while (resultSet.next()) {
                       cuisines.add(new Cuisine(resultSet.getLong("id"), resultSet.getString("name")));
                    }
                    restaurant.setCuisines(cuisines);
                }
            }
            sqlQuery = "SELECT t3.id AS country_id, t3.name AS country_name, t1.id AS city_id, t1.name AS city_name " +
                        "FROM cities t1 " +
                        "INNER JOIN address t2 ON t1.id = t2.city_id " +
                        "INNER JOIN countries t3 ON t3.id = t1.country_id " +
                        "WHERE t2.restaurant_id = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    Set<City> cities = new LinkedHashSet<>();
                    while (resultSet.next()) {
                        cities.add(new City(resultSet.getLong("city_id"), resultSet.getString("city_name"), new Country(resultSet.getLong("country_id"), resultSet.getString("country_name"))));
                    }
                    restaurant.setAddresses(cities);
                }
            }
            return restaurant;
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }

    @Override
    public Set<Restaurant> getAll() throws ExceptionDao {
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT `id`, `name`, `website`, `short_information`, `image` FROM restaurants ORDER BY `id` ASC;";
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
            String sqlQuery = "SELECT `id`, `name`, `website`, `short_information`, `image` FROM restaurants WHERE LOWER(`name`) LIKE ? ORDER BY `id` ASC;";
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
            String sqlQuery = "SELECT t3.id, t3.name, t3.website, t3.short_information, t3.image " +
                                "FROM restaurant_cuisine t1 " +
                                "INNER JOIN cuisines t2 ON t1.cuisine_id = t2.id " +
                                "INNER JOIN restaurants t3 ON t1.restaurant_id = t3.id " +
                                "WHERE LOWER(t2.name) LIKE ?;";
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
            String sqlQuery = "SELECT t1.id, t1.name, t1.website, t1.short_information, t1.image " +
                                "FROM restaurants t1 " +
                                "INNER JOIN address t2 ON t1.id = t2.restaurant_id " +
                                "INNER JOIN cities t3 ON t2.city_id = t3.id " +
                                "WHERE LOWER(t3.name) LIKE ? " +
                                "ORDER BY t1.id ASC";
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
            String sqlQuery = "SELECT t1.id, t1.name, t1.website, t1.short_information, t1.image " +
                                "FROM restaurants t1 " +
                                "INNER JOIN address t2 ON t1.id = t2.restaurant_id " +
                                "INNER JOIN cities t3 ON t2.city_id = t3.id " +
                                "INNER JOIN countries t4 ON t3.country_id = t4.id " +
                                "WHERE LOWER(t4.name) LIKE ? " +
                                "ORDER BY t1.id ASC;";
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
    public List<Comment> getRestaurantComments(long id) throws ExceptionDao {
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.comment, t1.date, t3.username " +
                    "FROM comments t1 " +
                    "INNER JOIN restaurants t2 ON t1.restaurant_id = t2.id " +
                    "INNER JOIN users t3 ON t1.user_id = t3.id " +
                    "WHERE t2.id = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Comment comment = new Comment();
                        comment.setComment(resultSet.getString("comment"));
                        comment.setDate(resultSet.getDate("date"));
                        comment.setUser(new User(resultSet.getString("username")));
                        comments.add(comment);
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
