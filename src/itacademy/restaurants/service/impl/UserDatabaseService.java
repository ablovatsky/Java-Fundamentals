package itacademy.restaurants.service.impl;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.UserDao;
import itacademy.restaurants.dao.impl.UserDatabaseDao;
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

    private UserDao userDao;

    private static volatile UserDatabaseService instance;

    private UserDatabaseService() {
        userDao = new UserDatabaseDao();
    }

    public static UserDatabaseService getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseService.class) {
                if (instance == null) {
                    instance = new UserDatabaseService();
                }
            }
        }
        return instance;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) throws ExceptionDao, SQLException {
        return this.userDao.getUserByNameAndPassword(username, password);
    }

    @Override
    public Set<Role> getUserRoles(User user) throws ExceptionDao {

        return this.userDao.getUserRoles(user);
    }

    @Override
    public void add(User user) throws ExceptionService, ExceptionDao, SQLException {
        this.userDao.add(user);
    }

    @Override
    public boolean update(User user) {
        return this.userDao.update(user);
    }

    @Override
    public boolean remove(User user) {
        return this.userDao.remove(user);
    }


    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return this.userDao.getAll();
    }
}
