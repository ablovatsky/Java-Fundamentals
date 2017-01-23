package itacademy.restaurants.service;


import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.model.City;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 06.01.2017.
 */
public interface RestaurantService extends ModelService<Restaurant> {

    Set<Restaurant> getRestaurantsByName(String name);

    Set<Cuisine> getRestaurantCuisines(Restaurant restaurant);

    void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine);

    void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine);

    Set<Restaurant> getRestaurantsByCuisine(String name);

    Set<City> getRestaurantAddresses(Restaurant restaurant);

    void addAddressToRestaurant(Restaurant restaurant, City city);

    void removeAddressFromRestaurant(Restaurant restaurant, City city);

    Set<Restaurant> getRestaurantsByCity(String name);

    Set<Restaurant> getRestaurantsByCountry(String name);

}
