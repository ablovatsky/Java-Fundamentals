package itacademy.restaurants.service;



import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.Model;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 11.01.2017.
 */
public interface ModelService<T extends Model> {

    void add(T model);

    boolean update(T model);

    boolean remove(T model);

    T getById(long id);

    Set<T> getAll();
}
