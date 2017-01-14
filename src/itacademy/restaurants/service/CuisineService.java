package itacademy.restaurants.service;

import itacademy.restaurants.model.Cuisine;

/**
 * Created by aVa on 05.01.2017.
 */
public interface CuisineService extends ModelService<Cuisine> {

    Cuisine getCuisineByName(String name);
}
