package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.impl.UserDatabaseDao;
import itacademy.restaurants.model.Model;
import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;
import itacademy.restaurants.service.ExceptionService;
import itacademy.restaurants.service.UserService;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */
public class UserDatabaseService implements UserService {

    private UserDatabaseDao userDatabaseDao;

    public UserDatabaseService() {
        userDatabaseDao = new UserDatabaseDao();
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) throws ExceptionDao, SQLException {
        return this.userDatabaseDao.getUserByNameAndPassword(username, password);
    }

    @Override
    public Set<Role> getUserRoles(User user) throws ExceptionDao {

        return this.userDatabaseDao.getUserRoles(user);
    }

    @Override
    public void add(User user) throws ExceptionService, ExceptionDao, SQLException {
        this.userDatabaseDao.add(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }
}
