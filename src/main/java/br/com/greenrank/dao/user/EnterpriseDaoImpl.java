package br.com.greenrank.dao.user;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.EnterpriseNotFoundException;
import br.com.greenrank.exceptions.EnterpriseNotSavedException;
import br.com.greenrank.model.user.Enterprise;
import oracle.jdbc.OracleArray;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnterpriseDaoImpl implements EnterpriseDao {
    private final Logger logger = Logger.getLogger(EnterpriseDaoImpl.class.getName());
    @Override
    public Enterprise save(Enterprise enterprise, Connection connection) throws SQLException, EnterpriseNotSavedException {
        final String sql =
                """
                BEGIN
                    INSERT INTO t_gr_enterprise (nm_legal, tr_name, nr_cnpj, tp_company, id_user)
                    VALUES (?, ?, ?, ?, ?)
                    RETURNING id_enterprise INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, enterprise.getLegalName());
        call.setString(2, enterprise.getTradeName());
        call.setString(3, enterprise.getCnpj());
        call.setString(4, enterprise.getCompanyType());
        call.setObject(5, enterprise.getId());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);
        if(linhasAlteradas == 0 || id == 0) {
            throw new EnterpriseNotSavedException();
        }
        return enterprise;
    }

    @Override
    public List<Enterprise> findAll() {
        final String sql = "SELECT * FROM t_gr_enterprise";
        final List<Enterprise> all = new ArrayList<Enterprise>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            CallableStatement call = connection.prepareCall(sql);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Enterprise enterprise = new Enterprise(
                        rs.getLong("id_enterprise"),
                        rs.getString("nm_legal"),
                        rs.getString("tr_name"),
                        rs.getString("nr_cnpj"),
                        rs.getString("tp_company"),
                        rs.getLong("id_user")
                );
                all.add(enterprise);
            }
        } catch (SQLException e){
            logger.severe("No records found on Enterprise: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Enterprise update(Enterprise enterprise, Connection connection) throws SQLException, EnterpriseNotFoundException {
        final String sql = """
                UPDATE t_gr_enterprise SET nm_legal = ?, tr_name=?, nr_cnpj=?, tp_company=?, id_user=?
                WHERE id_enterprise = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, enterprise.getLegalName());
        ps.setString(2, enterprise.getTradeName());
        ps.setString(3, enterprise.getCnpj());
        ps.setString(4, enterprise.getCompanyType());
        ps.setLong(5, enterprise.getIdUser());
        ps.setLong(6, enterprise.getId());
        int linhasAlteradas = ps.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new EnterpriseNotFoundException();
        }
        return enterprise;
    }
    @Override
    public void deleteUsersWithEnterprise(long idUser, Connection connection) throws SQLException, EnterpriseNotFoundException {
        final String checkUserSql = "SELECT COUNT(*) FROM T_GR_USER WHERE id_user = ?";
        try (PreparedStatement psCheck = connection.prepareStatement(checkUserSql)) {
            psCheck.setLong(1, idUser);
            ResultSet rs = psCheck.executeQuery();
            if(rs.next() && rs.getInt(1) == 0)  {
                throw new SQLException("No id user found: " + idUser);
            }
        }

        final String deleteUserSql = "DELETE FROM T_GR_USER WHERE id_user = ?";
        try (PreparedStatement psDelete = connection.prepareStatement(deleteUserSql)) {
            psDelete.setLong(1, idUser);
            int linhasAlteradas = psDelete.executeUpdate();
            if(linhasAlteradas == 0) {
                throw new EnterpriseNotFoundException();
            }
        }

    }

}
