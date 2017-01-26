package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.dao.impl.RestaurantDatabaseDao;
import itacademy.restaurants.model.*;
import itacademy.restaurants.service.ExceptionService;
import itacademy.restaurants.service.RestaurantService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 21.01.2017.
 */
public class RestaurantDatabaseService implements RestaurantService {

    private RestaurantDao restaurantDao;

    private static volatile RestaurantDatabaseService instance;

    private RestaurantDatabaseService() {
        restaurantDao = new RestaurantDatabaseDao();
    }

    public static RestaurantDatabaseService getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseService.class) {
                if (instance == null) {
                    instance = new RestaurantDatabaseService();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(Restaurant restaurant) throws ExceptionService, ExceptionDao, SQLException {
        this.restaurantDao.add(restaurant);
    }

    @Override
    public boolean update(Restaurant restaurant) {
        return false;
    }

    @Override
    public boolean remove(Restaurant restaurant) {
        return false;
    }

    @Override
    public Restaurant getById(long id) {
        return this.restaurantDao.getById(id);
    }

    @Override
    public Set<Restaurant> getAll() {
        return this.restaurantDao.getAll();
    }

    @Override
    public Set<Restaurant> getRestaurantsByName(String name) {
        return this.restaurantDao.getRestaurantsByName(name);
    }

    @Override
    public Set<Cuisine> getRestaurantCuisines(Restaurant restaurant) {
        return null;
    }

    @Override
    public void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine) {

    }

    @Override
    public void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine) {

    }

    @Override
    public Set<Restaurant> getRestaurantsByCuisine(String name) {
        return this.restaurantDao.getRestaurantsByCuisine(name);
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
        return this.restaurantDao.getRestaurantsByCity(name);
    }

    @Override
    public Set<Restaurant> getRestaurantsByCountry(String name) {
        return this.restaurantDao.getRestaurantsByCountry(name);
    }

    @Override
    public List<Comment> getRestaurantComments(long id) {
        return this.restaurantDao.getRestaurantComments(id);
    }

    @Override
    public void addCommentToRestaurant(Comment comment) {
        this.restaurantDao.addCommentToRestaurant(comment);
    }
}
