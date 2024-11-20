package br.com.greenrank.service.permission;

import br.com.greenrank.exceptions.PermissionNotFoundException;
import br.com.greenrank.exceptions.PermissionNotSavedException;
import br.com.greenrank.model.permission.Permission;

import java.sql.SQLException;
import java.util.List;

public interface PermissionService {
    Permission create(Permission permission) throws UnsupportedOperationException, SQLException, PermissionNotSavedException;
    List<Permission> getAll();
    Permission update(Permission permission) throws SQLException, PermissionNotFoundException;
    void deleteById(long id) throws SQLException, PermissionNotFoundException;
}
