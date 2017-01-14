package itacademy.restaurants.dao;


import itacademy.restaurants.model.Cuisine;

/**
 * Created by aVa on 06.01.2017.
 */
public interface CuisineDao extends ModelDao<Cuisine> {

    Cuisine getCuisineByName(String name);
}
