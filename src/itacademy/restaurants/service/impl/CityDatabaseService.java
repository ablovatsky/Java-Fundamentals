package itacademy.restaurants.service.impl;

import itacademy.restaurants.service.CityService;
import itacademy.restaurants.service.ExceptionService;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.impl.CityDatabaseDao;
import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Model;

import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CityDatabaseService implements CityService {

    private CityDatabaseDao cityDatabaseDao = new CityDatabaseDao();

    @Override
    public void add(City city) throws ExceptionService {
        try {
            this.cityDatabaseDao.add(city);
        } catch (ExceptionDao exceptionDao) {
            throw new ExceptionService("", exceptionDao);
        }
    }

    @Override
    public boolean update(City city) {
        return this.cityDatabaseDao.update(city);
    }

    @Override
    public boolean remove(City city) {
        return this.cityDatabaseDao.remove(city);
    }

    @Override
    public City getById(long id) {
        return this.cityDatabaseDao.getById(id);
    }

    @Override
    public Set<City> getAll() {
        return this.cityDatabaseDao.getAll();
    }

    @Override
    public City getCityByName(String name) {
        return this.cityDatabaseDao.getCityByName(name);
    }

    @Override
    public List<City> getCitiesByCountry(Country country) {
        return this.cityDatabaseDao.getCitiesByCountry(country);
    }
}
