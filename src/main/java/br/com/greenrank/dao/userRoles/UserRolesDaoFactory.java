package br.com.greenrank.dao.userRoles;

import br.com.greenrank.model.userRoles.UserRoles;

public class UserRolesDaoFactory {
    private UserRolesDaoFactory() {}
    public static UserRolesDao create() {
        return new UserRolesDaoImpl();
    }
}
