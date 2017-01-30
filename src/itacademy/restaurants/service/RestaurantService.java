package itacademy.restaurants.service;


import dto.RestaurantsListDto;
import itacademy.restaurants.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 06.01.2017.
 */
public interface RestaurantService extends ModelService<Restaurant> {



    Set<Cuisine> getRestaurantCuisines(Restaurant restaurant);

    void addCuisineToRestaurant(Restaurant restaurant, Cuisine cuisine);

    void removeCuisineFromRestaurant(Restaurant restaurant, Cuisine cuisine);

    Set<City> getRestaurantAddresses(Restaurant restaurant);

    void addAddressToRestaurant(Restaurant restaurant, City city);

    void removeAddressFromRestaurant(Restaurant restaurant, City city);

    RestaurantsListDto getRestaurantsByCuisine(String name);

    RestaurantsListDto getRestaurantsByName(String name);

    RestaurantsListDto getRestaurantsByCity(String name);

    RestaurantsListDto getRestaurantsByCountry(String name);

    List<Comment> getRestaurantComments(long id);

    void addCommentToRestaurant(Comment comment);

    RestaurantsListDto getRestaurants();

}
