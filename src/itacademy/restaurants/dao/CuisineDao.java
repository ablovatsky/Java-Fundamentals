package itacademy.restaurants.dao;

import itacademy.restaurants.model.Cuisine;


public interface CuisineDao extends ModelDao<Cuisine> {

    Cuisine getCuisineByName(String name) throws ExceptionDao;
}
