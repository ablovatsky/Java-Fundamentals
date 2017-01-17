package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CityDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.City;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CityDatabaseDao extends MySqlConnection implements CityDao {

    @Override
    public long add(City city) throws ExceptionDao {
        long id = 0;
        return id;
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void remove(City city) {

    }

    @Override
    public City getById(long id) {
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
