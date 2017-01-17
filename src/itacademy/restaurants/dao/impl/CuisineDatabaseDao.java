package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.dao.CuisineDao;
import itacademy.restaurants.model.Model;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CuisineDatabaseDao extends MySqlConnection implements CuisineDao {

    @Override
    public Cuisine getCuisineByName(String name) {
        return null;
    }

    @Override
    public long add(Cuisine cuisine) {
        long id = 0;
        return id;
    }

    @Override
    public void update(Cuisine cuisine) {

    }

    @Override
    public void remove(Cuisine cuisine) {

    }

    @Override
    public Cuisine getById(long id) {
        return null;
    }

    @Override
    public Set<Cuisine> getAll() {
        return null;
    }
}
