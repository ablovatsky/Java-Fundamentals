package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class RestaurantDatabaseDao extends MySqlConnection implements RestaurantDao {

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
        return null;
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
