package itacademy.restaurants.dao;

import itacademy.restaurants.model.Role;

/**
 * Created by aVa on 14.01.2017.
 */
public interface RoleDao extends ModelDao<Role> {

    Long getIdRoleByName(String name) throws ExceptionDao;
}
