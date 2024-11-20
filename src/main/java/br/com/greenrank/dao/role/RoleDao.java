package br.com.greenrank.dao.role;

import br.com.greenrank.exceptions.RoleNotSavedException;
import br.com.greenrank.model.roles.Roles;

import javax.management.relation.RoleNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    Roles save(Roles roles, Connection connection) throws UnsupportedOperationException, SQLException, RoleNotSavedException;
    List<Roles> listAll();
    Roles update(Roles roles, Connection connection) throws SQLException, RoleNotFoundException;
    void deleteById(Long id, Connection connection) throws SQLException, RoleNotFoundException;
}
