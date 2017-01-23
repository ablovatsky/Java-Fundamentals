package itacademy.restaurants.dao;

import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import java.util.List;


public interface CityDao extends ModelDao<City> {

    City getCityByName(String name) throws ExceptionDao;

    List<City> getCitiesByCountry(Country country) throws ExceptionDao;
}
