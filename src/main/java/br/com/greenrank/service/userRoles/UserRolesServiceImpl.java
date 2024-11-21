package br.com.greenrank.service.userRoles;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.userRoles.UserRolesDao;
import br.com.greenrank.dao.userRoles.UserRolesDaoFactory;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.exceptions.UserRolesNotFoundException;
import br.com.greenrank.exceptions.UserRolesNotSavedException;
import br.com.greenrank.model.userRoles.UserRoles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserRolesServiceImpl implements UserRolesService {

        private final UserRolesDao dao = UserRolesDaoFactory.create();

    @Override
    public UserRoles create(UserRoles userRoles) throws UnsupportedOperationException, SQLException, UserRolesNotSavedException {
        if(userRoles.getIdRole() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                userRoles = this.dao.save(userRoles, connection);
                connection.close();
                return userRoles;
            } catch (SQLException | UserRolesNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<UserRoles> getAll() {
        return this.dao.findAll();
    }

    @Override
    public UserRoles update(UserRoles userRoles) throws SQLException, UserRolesNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        userRoles = this.dao.update(userRoles, connection);
        connection.commit();
        return userRoles;
    }

    @Override
    public void deleteById(Long id) throws SQLException, UserRolesNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
