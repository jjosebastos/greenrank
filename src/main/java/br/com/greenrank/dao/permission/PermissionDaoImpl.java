package br.com.greenrank.dao.permission;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.PermissionNotFoundException;
import br.com.greenrank.exceptions.PermissionNotSavedException;
import br.com.greenrank.model.permission.Permission;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PermissionDaoImpl implements PermissionDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public Permission save(Permission permission, Connection connection) throws SQLException, PermissionNotSavedException {
    final String sql = """
            BEGIN
                INSERT INTO T_GR_PERMISSION(NM_PERMISSION, DS_PERMISSION, ID_USER)
                VALUES (?, ?, ?)
                RETURNING ID_PERMISSION INTO ?;
            END;
            """;
    CallableStatement call = connection.prepareCall(sql);
    call.setString(1, permission.getName());
    call.setString(2, permission.getDescription());
    call.setObject(3, permission.getId());
    call.registerOutParameter(4, OracleType.NUMBER);

        int rowsAffected = call.executeUpdate();
        long id = call.getLong(4);
        if (rowsAffected == 0 || id == 0) {
            throw new PermissionNotSavedException();
        }
        permission.setId(id);
        return permission;
    }

    @Override
    public List<Permission> listAll() {
        final String sql = "SELECT * FROM T_GR_PERMISSION";
        final List<Permission> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Permission permission = new Permission(
                        rs.getLong("id_permission"),
                        rs.getString("nm_permission"),
                        rs.getString("ds_permission"),
                        rs.getLong("id_user")
                );
                all.add(permission);
            }
        } catch (SQLException e){
            logger.severe("No records found on Permission: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Permission update(Permission permission, Connection connection) throws SQLException, PermissionNotFoundException {
        final String sql = """
                UPDATE T_GR_PERMISSION 
                    SET NM_PERMISSION = ?, DS_PERMISSION = ?, ID_USER = ?  
                WHERE id_permission = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, permission.getName());
        ps.setString(2, permission.getDescription());
        ps.setLong(3, permission.getIdUser());
        ps.setLong(4, permission.getId());

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected == 0) {
            throw new PermissionNotFoundException();
        }
        return permission;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, PermissionNotFoundException {
        final String sql = "DELETE FROM T_GR_PERMISSION WHERE id_permission = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new PermissionNotFoundException();
        }

    }
}
