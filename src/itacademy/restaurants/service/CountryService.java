package itacademy.restaurants.service;


import itacademy.restaurants.model.Country;

/**
 * Created by aVa on 08.01.2017.
 */
public interface CountryService  extends ModelService<Country>  {

    Country getCountryByName(String name);
}
