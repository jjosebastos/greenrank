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
        call.setObject(5, enterprise.getIdUser());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);
        if(linhasAlteradas == 0 || id == 0) {
            throw new EnterpriseNotSavedException();
        }
        enterprise.setId(id);
        return enterprise;
    }

    @Override
    public List<Enterprise> findAll() {
        final String sql = """
                SELECT  C.NM_USER, C.VL_PASSWORD, C.ID_EMAIL,
                        E.ID_ENTERPRISE, E.NM_LEGAL, E.TR_NAME,
                        E.NR_CNPJ, E.TP_COMPANY, E.ID_USER
                        FROM t_gr_user C
                        INNER JOIN T_GR_ENTERPRISE E ON C.ID_USER = E.ID_USER
                WHERE is_active = 'Y'
               """;
        final List<Enterprise> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            CallableStatement call = connection.prepareCall(sql);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Enterprise enterprise = new Enterprise(
                        rs.getLong("id_user"),
                        rs.getString("nm_user"),
                        rs.getString("vl_password"),
                        rs.getString("id_email"),
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


}
