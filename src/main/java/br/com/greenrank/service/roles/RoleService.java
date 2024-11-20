package br.com.greenrank.service.roles;

import br.com.greenrank.exceptions.RoleNotSavedException;
import br.com.greenrank.model.roles.Roles;

import javax.management.relation.RoleNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    Roles create(Roles roles) throws UnsupportedOperationException, SQLException, RoleNotSavedException;
    List<Roles> getAll();
    Roles update(Roles roles) throws SQLException, RoleNotFoundException;
    void deleteById(long id) throws SQLException, RoleNotFoundException;
}
