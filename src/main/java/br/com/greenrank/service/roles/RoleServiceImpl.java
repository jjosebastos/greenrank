package br.com.greenrank.service.roles;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.role.RoleDao;
import br.com.greenrank.dao.role.RoleDaoFactory;
import br.com.greenrank.exceptions.RoleNotSavedException;
import br.com.greenrank.model.roles.Roles;

import javax.management.relation.RoleNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleDao dao = RoleDaoFactory.create();
    @Override
    public Roles create(Roles roles) throws UnsupportedOperationException, SQLException, RoleNotSavedException {
        if(roles.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try{
                roles = this.dao.save(roles, connection);
                connection.commit();
                return roles;

            } catch (SQLException | RoleNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<Roles> getAll() {
        return this.dao.listAll();
    }

    @Override
    public Roles update(Roles roles) throws SQLException, RoleNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        roles = this.dao.update(roles, connection);
        connection.commit();
        return roles;
    }

    @Override
    public void deleteById(long id) throws SQLException, RoleNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
