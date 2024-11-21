package br.com.greenrank.dao.ecoPoint;

import br.com.greenrank.config.DatabaseConnection;
import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoPoint.EcoPoint;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EcoPointDaoImpl implements EcoPointDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public EcoPoint save(EcoPoint ecoPoint, Connection connection) throws SQLException, EcoPointNotSavedException {
        final String sql = """
                BEGIN 
                      INSERT INTO T_GR_ECO_POINT (nm_eco_point, nr_cnpj, hr_openning, hr_close)
                      VALUES (?, ?, ?, ?) RETURNING id_eco_point INTO ?; 
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, ecoPoint.getName());
        call.setString(2, ecoPoint.getCnpj());
        call.setTimestamp(3, new java.sql.Timestamp(ecoPoint.getHourOpen().getTime()));
        call.setTimestamp(4, new java.sql.Timestamp(ecoPoint.getHourClose().getTime()));

        call.registerOutParameter(5, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);
        if(linhasAlteradas == 0 || id == 0){
            throw new EcoPointNotSavedException();
        }
        ecoPoint.setId(id);
        return ecoPoint;
    }

    @Override
    public List<EcoPoint> findAll() {
        final String sql = "SELECT * FROM T_GR_ECO_POINT";
        List<EcoPoint> all = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EcoPoint ecoPoint = new EcoPoint(
                        rs.getLong("id_eco_point"),
                        rs.getString("nm_eco_point"),
                        rs.getString("nr_cnpj"),
                        rs.getDate("hr_openning"),
                        rs.getDate("hr_close")
                );
                all.add(ecoPoint);
            }
        } catch (SQLException s){
            logger.severe("No records found on Eco Point: " + s.getMessage());
        }

        return all;
    }

    @Override
    public EcoPoint update(EcoPoint ecoPoint, Connection connection) throws SQLException, EcoPointNotFoundException {
        final String sql  = """
            UPDATE T_GR_ECO_POINT 
                SET nm_eco_point = ?, nr_cnpj = ?,hr_openning = ?, hr_close = ? 
            WHERE id_eco_point = ?
            """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ecoPoint.getName());
        ps.setString(2, ecoPoint.getCnpj());
        ps.setTimestamp(3, new java.sql.Timestamp(ecoPoint.getHourOpen().getTime()));
        ps.setTimestamp(4, new java.sql.Timestamp(ecoPoint.getHourClose().getTime()));
        ps.setLong(5, ecoPoint.getId());

        int linhasAlteradas = ps.executeUpdate();
        if(linhasAlteradas == 0){
            throw new EcoPointNotFoundException();
        }
        return ecoPoint;
    }

    @Override
    public void deleteById(long id, Connection connection) throws SQLException, EcoPointNotFoundException {
        final String sql = "DELETE FROM T_GR_ECO_POINT WHERE id_eco_point = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        int linhasAlteradas = ps.executeUpdate();
        if(linhasAlteradas == 0){
            throw new EcoPointNotFoundException();
        }
    }
}
