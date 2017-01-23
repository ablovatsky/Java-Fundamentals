package itacademy.restaurants.dao;

import itacademy.restaurants.model.Role;


public interface RoleDao extends ModelDao<Role> {

    Long getIdRoleByName(String name) throws ExceptionDao;
}
