package br.com.greenrank.service.permission;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.permission.PermissionDao;
import br.com.greenrank.dao.permission.PermissionDaoFactory;
import br.com.greenrank.exceptions.PermissionNotFoundException;
import br.com.greenrank.exceptions.PermissionNotSavedException;
import br.com.greenrank.model.permission.Permission;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao dao = PermissionDaoFactory.create();
    @Override
    public Permission create(Permission permission) throws UnsupportedOperationException, SQLException, PermissionNotSavedException {
        if(permission.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                permission = this.dao.save(permission, connection);
                connection.commit();
                return permission;
            } catch (SQLException | PermissionNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<Permission> getAll() {
        return this.dao.listAll();
    }

    @Override
    public Permission update(Permission permission) throws SQLException, PermissionNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        permission = this.dao.update(permission, connection);
        connection.commit();
        return permission;
    }

    @Override
    public void deleteById(long id) throws SQLException, PermissionNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
