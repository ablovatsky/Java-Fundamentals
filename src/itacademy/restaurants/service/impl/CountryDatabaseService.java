package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.CountryDao;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.impl.CountryDatabaseDao;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.service.CountryService;
import itacademy.restaurants.service.ExceptionService;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by VAblovatsky on 27.01.2017.
 */
public class CountryDatabaseService implements CountryService {

    private CountryDao countryDao;

    private static volatile CountryDatabaseService instance;

    private CountryDatabaseService() {
        countryDao = new CountryDatabaseDao();
    }

    public static CountryDatabaseService getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseService.class) {
                if (instance == null) {
                    instance = new CountryDatabaseService();
                }
            }
        }
        return instance;
    }

    @Override
    public Country getCountryByName(String name) {
        return null;
    }

    @Override
    public void add(Country model) {

    }

    @Override
    public boolean update(Country model) {
        return false;
    }

    @Override
    public boolean remove(Country model) {
        return false;
    }

    @Override
    public Country getById(long id) {
        return null;
    }

    @Override
    public Set<Country> getAll() {
        return this.countryDao.getAll();
    }
}
