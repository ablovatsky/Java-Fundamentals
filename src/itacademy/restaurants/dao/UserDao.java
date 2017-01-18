package itacademy.restaurants.dao;


import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 06.01.2017.
 */
public interface UserDao extends ModelDao<User> {

    long getUserIdByName (String username) throws ExceptionDao;

    User getUserByNameAndPassword(String username, String password) throws ExceptionDao;

    Set<Role> getUserRoles(User user) throws ExceptionDao;
}
