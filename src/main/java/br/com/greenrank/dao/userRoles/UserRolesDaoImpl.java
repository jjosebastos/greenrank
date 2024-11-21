package br.com.greenrank.dao.userRoles;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.UserRolesNotFoundException;
import br.com.greenrank.exceptions.UserRolesNotSavedException;
import br.com.greenrank.model.userRoles.UserRoles;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRolesDaoImpl implements UserRolesDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public UserRoles save(UserRoles userRoles, Connection connection) throws SQLException, UserRolesNotSavedException {
        final String sql = "BEGIN INSERT INTO T_GR_USER_ROLES (id_user, id_role) VALUES (?, ?) RETURNING ID_USER_ROLE INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setObject(1, userRoles.getIdUser());
        call.setObject(2, userRoles.getIdRole());
        call.registerOutParameter(3, OracleType.NUMBER);
        int rowsAffected = call.executeUpdate();
        long id = call.getLong(3);
        if (rowsAffected == 0 || id == 0) {
            throw new UserRolesNotSavedException();
        }
        userRoles.setIdUser(id);
        return userRoles;
    }

    @Override
    public List<UserRoles> findAll() {
        final String sql = "SELECT * FROM T_GR_USER_ROLES";
        final List<UserRoles> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserRoles userRoles = new UserRoles(
                        rs.getLong("id_user_roles"),
                        rs.getLong("id_user"),
                        rs.getLong("id_role")
                );
                all.add(userRoles);
            }
        } catch (SQLException e){
            logger.severe("No records found on User Roles: " + e.getMessage());
        }
        return all;
    }

    @Override
    public UserRoles update(UserRoles userRoles, Connection connection) throws SQLException, UserRolesNotFoundException {
        final String sql = "UPDATE T_GR_USER_ROLES SET id_user = ?, id_role = ? WHERE id_user_roles = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, userRoles.getIdUser());
        stmt.setLong(2, userRoles.getIdRole());
        stmt.setLong(3, userRoles.getIdUser());
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new UserRolesNotFoundException();
        }
        return userRoles;
    }

    @Override
    public void deleteById(long id, Connection connection) throws SQLException, UserRolesNotFoundException {
        final String sql = "DELETE FROM T_GR_USER_ROLES WHERE id_user_roles = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new UserRolesNotFoundException();
        }
    }
}
