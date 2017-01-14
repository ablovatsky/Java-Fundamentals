package itacademy.restaurants.dao;


import itacademy.restaurants.model.Country;

/**
 * Created by aVa on 08.01.2017.
 */
public interface CountryDao extends ModelDao<Country> {

    Country getCountryByName(String name);
}
