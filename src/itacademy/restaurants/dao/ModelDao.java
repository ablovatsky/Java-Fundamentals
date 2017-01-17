package itacademy.restaurants.dao;


import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Model;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 11.01.2017.
 */
public interface ModelDao<T extends Model> {

    public long add(T model) throws ExceptionDao, SQLException;

    public void update(T model);

    public void remove(T model);

    public T getById(long id);

    public Set<T> getAll();
}
