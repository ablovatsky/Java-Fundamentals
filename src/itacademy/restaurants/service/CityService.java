package itacademy.restaurants.service;


import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;


import java.util.List;
import java.util.Map;

/**
 * Created by aVa on 08.01.2017.
 */
public interface CityService  extends ModelService<City>  {

    City getCityByName(String name);

    List<City> getCitiesByCountry(Country country);


}
