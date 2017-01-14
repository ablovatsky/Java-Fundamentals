package itacademy.restaurants.dao;


import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import java.util.List;

/**
 * Created by aVa on 08.01.2017.
 */
public interface CityDao extends ModelDao<City> {

    City getCityByName(String name);

    List<City> getCitiesByCountry(Country country);
}
