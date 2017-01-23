package itacademy.restaurants.dao;

import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.model.Restaurant;
import java.util.List;
import java.util.Set;


public interface RestaurantDao extends ModelDao<Restaurant> {

    Set<Restaurant> getRestaurantsByName(String name) throws ExceptionDao;

    Set<Cuisine> getRestaurantCuisines(Restaurant restaurant) throws ExceptionDao;

    void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine) throws ExceptionDao;

    void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine) throws ExceptionDao;

    Set<Restaurant> getRestaurantsByCuisine(String name) throws ExceptionDao;

    Set<City> getRestaurantAddresses(Restaurant restaurant) throws ExceptionDao;

    void addAddressToRestaurant(Restaurant restaurant, City city) throws ExceptionDao;

    void removeAddressFromRestaurant(Restaurant restaurant, City city) throws ExceptionDao;

    Set<Restaurant> getRestaurantsByCity(String name) throws ExceptionDao;

    Set<Restaurant> getRestaurantsByCountry(String name) throws ExceptionDao;

}
