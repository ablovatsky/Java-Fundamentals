package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CityDao;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.City;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CityDatabaseDao implements CityDao {

    @Override
    public void add(City city) throws ExceptionDao {
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void remove(City city) {

    }

    @Override
    public City getById() {
        return null;
    }

    @Override
    public Set<City> getAll() {
        return null;
    }

    @Override
    public City getCityByName(String name) {
        return null;
    }

    @Override
    public List<City> getCitiesByCountry(Country country) {
        return null;
    }
}
