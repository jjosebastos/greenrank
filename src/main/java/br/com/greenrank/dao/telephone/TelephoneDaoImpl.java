package br.com.greenrank.dao.telephone;

import br.com.greenrank.config.DatabaseConnection;
import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.TelephoneNotFoundException;
import br.com.greenrank.exceptions.TelephoneNotSavedException;
import br.com.greenrank.model.telephone.Telephone;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TelephoneDaoImpl implements TelephoneDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public Telephone save(Telephone telephone, Connection connection) throws TelephoneNotSavedException, SQLException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_GR_TELEPHONE (nr_ddd,nr_telephone,tp_telephone,id_eco_point,id_user)
                    VALUES (?,?,?,?,?)
                    RETURNING id_eco_point INTO ?;
                END;    
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, telephone.getDdd());
        call.setString(2, telephone.getNumber());
        call.setString(3, telephone.getType());
        call.setObject(4, telephone.getIdEcoPoint());
        call.setObject(5, telephone.getIdUser());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);
        if(linhasAlteradas == 0 || id == 0 ) {
            throw new TelephoneNotSavedException();
        }
        return telephone;
    }

    @Override
    public List<Telephone> findAll() {
        final String sql = "SELECT * FROM T_GR_TELEPHONE";
        List<Telephone> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Telephone telephone = new Telephone(
                        rs.getLong("id_telephone"),
                        rs.getString("nr_ddd"),
                        rs.getString("nr_telephone"),
                        rs.getString("tp_telephone"),
                        rs.getLong("id_eco_point"),
                        rs.getLong("id_user")
                );
                all.add(telephone);
            }
        } catch (SQLException e) {
            logger.severe("No records found on Telephone: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Telephone update(Telephone telephone, Connection connection) throws TelephoneNotFoundException, SQLException {
        final String sql = """
                    UPDATE T_GR_TELEPHONE 
                        SET NR_DDD=?, NR_TELEPHONE=?, TP_TELEPHONE=?, ID_ECO_POINT=?, ID_USER=? 
                    WHERE id_eco_point=?
                    """;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, telephone.getDdd());
        pstmt.setString(2, telephone.getNumber());
        pstmt.setString(3, telephone.getType());
        pstmt.setObject(4, telephone.getIdEcoPoint());
        pstmt.setObject(5, telephone.getIdUser());
        pstmt.setLong(6, telephone.getId());
        int linhasAlteradas = pstmt.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new TelephoneNotFoundException();
        }
        return telephone;
    }

    @Override
    public void delete(long id, Connection connection) throws TelephoneNotFoundException, SQLException {
        final String sql = "DELETE FROM T_GR_TELEPHONE WHERE id_eco_point=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        int linhasAlteradas = pstmt.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new TelephoneNotFoundException();
        }
    }
}
