package br.com.greenrank.dao.permission;

import br.com.greenrank.exceptions.PermissionNotFoundException;
import br.com.greenrank.exceptions.PermissionNotSavedException;
import br.com.greenrank.model.permission.Permission;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PermissionDao {
    Permission save(Permission permission, Connection connection)throws SQLException, PermissionNotSavedException;
    List<Permission> listAll();
    Permission update(Permission permission, Connection connection) throws SQLException, PermissionNotFoundException;
    void deleteById(Long id, Connection connection)throws SQLException, PermissionNotFoundException;
}
