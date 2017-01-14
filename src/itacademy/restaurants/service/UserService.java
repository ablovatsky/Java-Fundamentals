package itacademy.restaurants.service;


import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 04.01.2017.
 */
public interface UserService extends ModelService<User> {

    User getUserByName(String username);

    User getUserByNameAndPassword(String username, String password) throws ExceptionDao, SQLException;

    Set<Role> getUserRoles(User user) throws ExceptionDao;
}
