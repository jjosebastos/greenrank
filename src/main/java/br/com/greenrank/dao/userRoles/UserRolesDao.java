package br.com.greenrank.dao.userRoles;

import br.com.greenrank.exceptions.UserRolesNotFoundException;
import br.com.greenrank.exceptions.UserRolesNotSavedException;
import br.com.greenrank.model.userRoles.UserRoles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRolesDao {
    UserRoles save(UserRoles userRoles, Connection connection) throws SQLException, UserRolesNotSavedException;
    List<UserRoles> findAll();
    UserRoles update(UserRoles userRoles, Connection connection) throws SQLException, UserRolesNotFoundException;
    void deleteById(long id, Connection connection) throws SQLException, UserRolesNotFoundException;
}
