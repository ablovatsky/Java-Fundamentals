package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CountryDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Country;
import itacademy.restaurants.model.Model;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CountryDatabaseDao extends MySqlConnection implements CountryDao {

    @Override
    public Country getCountryByName(String name) {
        return null;
    }

    @Override
    public long add(Country country) {
        long id = 0;
        return id;
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void remove(Country country) {

    }

    @Override
    public Country getById(long id) {
        return null;
    }

    @Override
    public Set<Country> getAll() {
        return null;
    }
}
