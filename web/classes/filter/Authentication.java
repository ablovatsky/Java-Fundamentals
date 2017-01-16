package filter;


import itacademy.restaurants.model.User;

import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */

public class Authentication <T extends AuthenticationRoles>{

    public boolean isAccess(User user, Set<T> roles) {
        return user.getRoles().stream().filter((role) -> {
            for (T roleAuth : roles) {
                if (role.getName().toLowerCase().equals(roleAuth.name().toLowerCase())) {
                    return true;
                }
            }
            return false;
        }).count() == roles.size();
    }

}
