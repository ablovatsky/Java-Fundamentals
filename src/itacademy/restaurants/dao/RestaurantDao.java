package itacademy.restaurants.dao;

import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.model.Restaurant;
import java.util.List;
import java.util.Set;


public interface RestaurantDao extends ModelDao<Restaurant> {

    Restaurant getRestaurantByName(String name) throws ExceptionDao;

    Set<Cuisine> getRestaurantCuisines(Restaurant restaurant) throws ExceptionDao;

    void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine) throws ExceptionDao;

    void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine) throws ExceptionDao;

    List<Restaurant> getRestaurantsByCuisine(Cuisine cuisine) throws ExceptionDao;

    Set<City> getRestaurantAddresses(Restaurant restaurant) throws ExceptionDao;

    void addAddressToRestaurant(Restaurant restaurant, City city) throws ExceptionDao;

    void removeAddressFromRestaurant(Restaurant restaurant, City city) throws ExceptionDao;

    List<Restaurant> getRestaurantsByCity(City city) throws ExceptionDao;

    List<Restaurant> getRestaurantsByCountry(Country country) throws ExceptionDao;

}
