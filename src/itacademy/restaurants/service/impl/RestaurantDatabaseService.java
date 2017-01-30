package itacademy.restaurants.service.impl;

import dto.RestaurantsListDto;
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
    public void add(Restaurant restaurant)  {
        this.restaurantDao.add(restaurant);
    }

    @Override
    public boolean update(Restaurant restaurant) {
        return this.restaurantDao.update(restaurant);
    }

    @Override
    public boolean remove(Restaurant restaurant) {
        return this.restaurantDao.remove(restaurant);
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
    public RestaurantsListDto getRestaurantsByCuisine(String name) {
        return new RestaurantsListDto(this.restaurantDao.getRestaurantsByCuisine(name));
    }

    @Override
    public RestaurantsListDto getRestaurantsByName(String name) {
        return new RestaurantsListDto(this.restaurantDao.getRestaurantsByName(name));
    }

    @Override
    public RestaurantsListDto getRestaurantsByCity(String name) {
        return new RestaurantsListDto(this.restaurantDao.getRestaurantsByCity(name));
    }

    @Override
    public RestaurantsListDto getRestaurantsByCountry(String name) {
        return new RestaurantsListDto(this.restaurantDao.getRestaurantsByCountry(name));
    }

    @Override
    public List<Comment> getRestaurantComments(long id) {
        return this.restaurantDao.getRestaurantComments(id);
    }

    @Override
    public void addCommentToRestaurant(Comment comment) {
        this.restaurantDao.addCommentToRestaurant(comment);
    }

    @Override
    public RestaurantsListDto getRestaurants() {
        return new RestaurantsListDto(this.restaurantDao.getAll());
    }
}
