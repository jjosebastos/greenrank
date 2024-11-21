package br.com.greenrank.service.userRoles;

import br.com.greenrank.exceptions.UserRolesNotFoundException;
import br.com.greenrank.exceptions.UserRolesNotSavedException;
import br.com.greenrank.model.userRoles.UserRoles;

import java.sql.SQLException;
import java.util.List;

public interface UserRolesService {
    UserRoles create(UserRoles userRoles) throws UnsupportedOperationException, SQLException, UserRolesNotSavedException;
    List<UserRoles> getAll();
    UserRoles update(UserRoles userRoles) throws SQLException, UserRolesNotFoundException;
    void deleteById(Long id) throws SQLException, UserRolesNotFoundException;
}
