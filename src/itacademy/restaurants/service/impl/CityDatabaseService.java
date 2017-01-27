package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.CityDao;
import itacademy.restaurants.dao.impl.CityDatabaseDao;
import itacademy.restaurants.service.CityService;
import itacademy.restaurants.service.ExceptionService;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CityDatabaseService implements CityService {

    private CityDao cityDao;

    private static volatile CityDatabaseService instance;

    private CityDatabaseService() {
        cityDao = new CityDatabaseDao();
    }

    public static CityDatabaseService getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseService.class) {
                if (instance == null) {
                    instance = new CityDatabaseService();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(City city) throws ExceptionService {
        try {
            this.cityDao.add(city);
        } catch (ExceptionDao exceptionDao) {
            throw new ExceptionService("", exceptionDao);
        }
    }

    @Override
    public boolean update(City city) {
        return this.cityDao.update(city);
    }

    @Override
    public boolean remove(City city) {
        return this.cityDao.remove(city);
    }

    @Override
    public City getById(long id) {
        return this.cityDao.getById(id);
    }

    @Override
    public Set<City> getAll() {
        return this.cityDao.getAll();
    }

    @Override
    public City getCityByName(String name) {
        return this.cityDao.getCityByName(name);
    }

    @Override
    public List<City> getCitiesByCountry(Country country) {
        return this.cityDao.getCitiesByCountry(country);
    }


}
