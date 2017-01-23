package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.CuisineDao;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.impl.CuisineDatabaseDao;
import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.ExceptionService;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by VAblovatsky on 23.01.2017.
 */
public class CuisineDatabaseService implements CuisineService {

    private CuisineDao cuisineDao;

    private static volatile CuisineDatabaseService instance;

    private CuisineDatabaseService() {
        cuisineDao = new CuisineDatabaseDao();
    }

    public static CuisineDatabaseService getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseService.class) {
                if (instance == null) {
                    instance = new CuisineDatabaseService();
                }
            }
        }
        return instance;
    }

    @Override
    public Cuisine getCuisineByName(String name) {
        return null;
    }

    @Override
    public void add(Cuisine model) throws ExceptionService, ExceptionDao, SQLException {

    }

    @Override
    public boolean update(Cuisine model) {
        return false;
    }

    @Override
    public boolean remove(Cuisine model) {
        return false;
    }

    @Override
    public Cuisine getById(long id) {
        return null;
    }

    @Override
    public Set<Cuisine> getAll() {
        return this.cuisineDao.getAll();
    }
}
