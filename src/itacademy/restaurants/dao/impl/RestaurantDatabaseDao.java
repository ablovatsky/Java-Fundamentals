package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.*;

import java.sql.*;
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
        Connection connection = connections.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String sqlQuery = "INSERT INTO `restaurants` (`name`, `phone`, `website`, `working_hours`, `short_information`, `information`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, restaurant.getName());
                statement.setString(2, restaurant.getPhone());
                statement.setString(3, restaurant.getWebsite());
                statement.setString(4, restaurant.getWorkingHours());
                statement.setString(5, restaurant.getShortInformation());
                statement.setString(6, restaurant.getInformation());
                statement.setBlob(7, restaurant.getLoadingImage());
                statement.executeUpdate();
                try(ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                    }
                }
            }
            sqlQuery = "INSERT INTO `restaurant_cuisine` (`restaurant_id`, `cuisine_id`) VALUES (?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                for (Cuisine cuisine : restaurant.getCuisines()) {
                    statement.setLong(1, id);
                    statement.setLong(2, cuisine.getId());
                    statement.executeUpdate();
                }
            }
            sqlQuery = "INSERT INTO `address` (`restaurant_id`, `city_id`) VALUES (?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                for (City city : restaurant.getAddresses()) {
                    statement.setLong(1, id);
                    statement.setLong(2, city.getId());
                    statement.executeUpdate();
                }
            }
            connections.commitConnection(connection);
        } catch (SQLException e) {
            connections.rollbackConnection(connection);
            throw new ExceptionDao("",e);
        } finally {
            connections.closeConnection(connection);
        }
        return id;
    }

    @Override
    public boolean update(Restaurant restaurant) throws ExceptionDao {
        Connection connection = connections.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String sqlQuery = "UPDATE `restaurants`SET `name` = ?, `phone` = ?, `website` = ?, `working_hours` = ?, `short_information` = ?, `information` = ?, `image` = ? WHERE `id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, restaurant.getName());
                statement.setString(2, restaurant.getPhone());
                statement.setString(3, restaurant.getWebsite());
                statement.setString(4, restaurant.getWorkingHours());
                statement.setString(5, restaurant.getShortInformation());
                statement.setString(6, restaurant.getInformation());
                statement.setBlob(7, restaurant.getLoadingImage());
                statement.setLong(8, restaurant.getId());
                statement.executeUpdate();
            }
            sqlQuery = "DELETE FROM `restaurant_cuisine` WHERE `restaurant_id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, restaurant.getId());
                statement.executeUpdate();

            }
            sqlQuery = "INSERT INTO `restaurant_cuisine` (`restaurant_id`, `cuisine_id`) VALUES (?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                for (Cuisine cuisine : restaurant.getCuisines()) {
                    statement.setLong(1, restaurant.getId());
                    statement.setLong(2, cuisine.getId());
                    statement.executeUpdate();
                }
            }
            sqlQuery = "DELETE FROM `address` WHERE `restaurant_id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, restaurant.getId());
                statement.executeUpdate();

            }
            sqlQuery = "INSERT INTO `address` (`restaurant_id`, `city_id`) VALUES (?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                for (City city : restaurant.getAddresses()) {
                    statement.setLong(1, restaurant.getId());
                    statement.setLong(2, city.getId());
                    statement.executeUpdate();
                }
            }
            connections.commitConnection(connection);
            return true;
        } catch (SQLException e) {
            connections.rollbackConnection(connection);
            throw new ExceptionDao("",e);
        } finally {
            connections.closeConnection(connection);
        }
    }

    @Override
    public boolean remove(Restaurant restaurant) throws ExceptionDao {
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "DELETE FROM `restaurants` WHERE `id` = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, restaurant.getId());
                statement.execute();
                return true;
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }

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
                        restaurant.setShortInformation(resultSet.getString("short_information"));
                        restaurant.setInformation(resultSet.getString("information"));
                        restaurant.setImage(resultSet.getBytes("image"));
                    }
                }
            }
            restaurant.setCuisines(getRestaurantCuisines(restaurant));
            restaurant.setAddresses(getRestaurantAddresses(restaurant));
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
            try(Statement statement = connection.prepareStatement(sqlQuery)) {
                try(ResultSet resultSet = statement.executeQuery(sqlQuery)) {
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
        try (Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.id, t1.name " +
                    "FROM cuisines t1 " +
                    "INNER JOIN restaurant_cuisine t2 ON t1.id = t2.cuisine_id " +
                    "WHERE t2.restaurant_id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, restaurant.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    Set<Cuisine> cuisines = new LinkedHashSet<>();
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
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t3.id AS country_id, t3.name AS country_name, t1.id AS city_id, t1.name AS city_name " +
                    "FROM cities t1 " +
                    "INNER JOIN address t2 ON t1.id = t2.city_id " +
                    "INNER JOIN countries t3 ON t3.id = t1.country_id " +
                    "WHERE t2.restaurant_id = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, restaurant.getId());
                try(ResultSet resultSet = statement.executeQuery()) {
                    Set<City> cities = new LinkedHashSet<>();
                    while (resultSet.next()) {
                        cities.add(new City(resultSet.getLong("city_id"), resultSet.getString("city_name"), new Country(resultSet.getLong("country_id"), resultSet.getString("country_name"))));
                    }
                    return cities;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
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
    public List<Comment> getRestaurantComments(long id) throws ExceptionDao{
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.comment, t1.date, t3.username " +
                    "FROM comments t1 " +
                    "INNER JOIN restaurants t2 ON t1.restaurant_id = t2.id " +
                    "INNER JOIN users t3 ON t1.user_id = t3.id " +
                    "WHERE t2.id = ? ORDER BY t1.date DESC;";
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

    @Override
    public void addCommentToRestaurant(Comment comment) throws ExceptionDao {
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "INSERT INTO `comments` (`restaurant_id`, `user_id`, `comment`) VALUES (?, ?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, comment.getRestaurant().getId());
                statement.setLong(2, comment.getUser().getId());
                statement.setString(3, comment.getComment());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }

    }
}
