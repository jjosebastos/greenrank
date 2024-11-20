package br.com.greenrank.dao.role;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.RoleNotSavedException;
import br.com.greenrank.model.roles.Roles;
import oracle.jdbc.OracleType;

import javax.management.relation.RoleNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoleDaoImpl implements RoleDao {
    private final Logger logger = Logger.getLogger(RoleDaoImpl.class.getName());
    @Override
    public Roles save(Roles roles, Connection connection) throws UnsupportedOperationException, SQLException, RoleNotSavedException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_GR_ROLES (nm_role, ds_role) VALUES (?, ?)
                    RETURNING id_role INTO?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, roles.getName());
        call.setString(2, roles.getDescription());
        call.registerOutParameter(3, OracleType.NUMBER);

        int rowsAffected = call.executeUpdate();
        long id = call.getLong(3);
        if(rowsAffected == 0 || id == 0) {
            throw new RoleNotSavedException();
        }
        roles.setId(id);
        return roles;
    }

    @Override
    public List<Roles> listAll() {
        final String sql = "SELECT * FROM T_GR_ROLES";
        final List<Roles> all = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Roles roles = new Roles(
                        rs.getLong("id_role"),
                        rs.getString("nm_role"),
                        rs.getString("ds_role")
                );
                all.add(roles);
            }
        } catch (SQLException e){
            logger.severe("No records found on roles: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Roles update(Roles roles, Connection connection) throws SQLException, RoleNotFoundException {
        final String sql = "UPDATE T_GR_ROLES SET nm_role = ?, ds_role = ? WHERE id_role = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, roles.getName());
        ps.setString(2, roles.getDescription());
        ps.setLong(3, roles.getId());
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected == 0) {
            throw new RoleNotFoundException();
        }
        return roles;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, RoleNotFoundException {
        final String sql = "DELETE FROM T_GR_ROLES WHERE id_role = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected == 0) {
            throw new RoleNotFoundException();
        }
    }
}
