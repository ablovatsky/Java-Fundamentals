package itacademy.restaurants.dao;

import itacademy.restaurants.model.Country;


public interface CountryDao extends ModelDao<Country> {

    Country getCountryByName(String name) throws ExceptionDao;
}
