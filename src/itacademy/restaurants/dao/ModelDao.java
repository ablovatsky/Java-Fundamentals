package itacademy.restaurants.dao;


import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Model;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 11.01.2017.
 */
public interface ModelDao<T extends Model> {

    public long add(T model) throws ExceptionDao;

    public boolean update(T model) throws ExceptionDao;

    public boolean remove(T model) throws ExceptionDao;

    public T getById(long id) throws ExceptionDao;

    public Set<T> getAll() throws ExceptionDao;
}
