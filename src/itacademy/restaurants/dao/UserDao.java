package itacademy.restaurants.dao;


import itacademy.restaurants.model.Role;
import itacademy.restaurants.model.User;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by aVa on 06.01.2017.
 */
public interface UserDao extends ModelDao<User> {

    User getUserByName(String username);

    User getUserByNameAndPassword(String username, String password) throws ExceptionDao, SQLException;

    Set<Role> getUserRoles(User user) throws ExceptionDao;
}
