package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.RestaurantDao;
import itacademy.restaurants.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class RestaurantDatabaseDao implements RestaurantDao {

    @Override
    public void add(Restaurant restaurant) {

    }

    @Override
    public void update(Restaurant restaurant) {

    }

    @Override
    public void remove(Restaurant restaurant) {

    }

    @Override
    public Restaurant getById() {
        return null;
    }

    @Override
    public Set<Restaurant> getAll() {
        return null;
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return null;
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
