package br.com.greenrank.dao.user;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.User;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public User save(User user, Connection connection) throws UserNotSavedException, SQLException {
        final String sql =
                """
                BEGIN INSERT INTO T_GR_USER(NM_USER, VL_PASSWORD,ID_EMAIL) VALUES (?,?,?) RETURNING ID INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, user.getUsername());
        call.setString(2, user.getPassword());
        call.setString(3, user.getEmail());
        call.registerOutParameter(4, OracleTypes.NUMBER);
        int linhasAlteradas = call.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new UserNotSavedException();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        final List<User> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_GR_USER";
        try(Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id_user"),
                        rs.getString("nm_user"),
                        rs.getString("vl_password"),
                        rs.getString("id_email"));
                all.add(user);
            }
        } catch (SQLException e){
            logger.warning("Nenhum registro de usuário encontrado: " + e.getMessage());
        }
        return all;
    }

    @Override
    public User update(User user, Connection connection) throws UserNotFoundException, SQLException {
        final String sql =
            """
            UPDATE T_GR_USER SET 
            nm_user = ?, vl_password = ?, id_email = ? WHERE id_user = ?
            """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setLong(4, user.getId());
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public void delete(Long id, Connection connection) throws UserNotFoundException, SQLException {
        final String sql = "DELETE FROM T_GR_USER WHERE id_user = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new UserNotFoundException();
        }
    }
}
