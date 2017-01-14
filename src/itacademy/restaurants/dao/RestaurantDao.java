package itacademy.restaurants.dao;


import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.model.Restaurant;
import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 06.01.2017.
 */
public interface RestaurantDao extends ModelDao<Restaurant> {

    Restaurant getRestaurantByName(String name);

    Set<Cuisine> getRestaurantCuisines(Restaurant restaurant);

    void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine);

    void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine);

    List<Restaurant> getRestaurantsByCuisine(Cuisine cuisine);

    Set<City> getRestaurantAddresses(Restaurant restaurant);

    void addAddressToRestaurant(Restaurant restaurant, City city);

    void removeAddressFromRestaurant(Restaurant restaurant, City city);

    List<Restaurant> getRestaurantsByCity(City city);

    List<Restaurant> getRestaurantsByCountry(Country country);

}
