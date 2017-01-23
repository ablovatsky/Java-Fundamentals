package itacademy.restaurants.dao;

import itacademy.restaurants.model.Model;
import java.util.Set;


public interface ModelDao<T extends Model> {

    long add(T model) throws ExceptionDao;

    boolean update(T model) throws ExceptionDao;

    boolean remove(T model) throws ExceptionDao;

    T getById(long id) throws ExceptionDao;

    Set<T> getAll() throws ExceptionDao;
}
