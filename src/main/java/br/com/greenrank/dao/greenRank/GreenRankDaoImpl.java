package br.com.greenrank.dao.greenRank;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.GreenRankNotFoundException;
import br.com.greenrank.exceptions.GreenRankNotSavedException;
import br.com.greenrank.model.greenRank.GreenRank;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GreenRankDaoImpl implements GreenRankDao {
    private final Logger logger = Logger.getLogger(GreenRankDaoImpl.class.getName());
    @Override
    public GreenRank save(GreenRank greenRank, Connection connection) throws GreenRankNotSavedException, SQLException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_GR_GREEN_RANK (DT_CONNECTION, DT_DISCONNECTION, ID_USER, ID_ECO_POINT)
                    VALUES (?, ?, ?, ?) RETURNING ID_GREEN_RANK INTO ?; 
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setObject(1, greenRank.getDateConnection());
        call.setObject(2, greenRank.getDateConnection());
        call.setObject(3, greenRank.getId());
        call.setObject(4, greenRank.getIdEcoPoint());
        call.registerOutParameter(5, OracleType.NUMBER);
        int linhasAlteradas = call.executeUpdate();
        long id = greenRank.getId();
        if(linhasAlteradas == 0 || id == 0) {
            throw new GreenRankNotSavedException();
        }
        return greenRank;
    }

    @Override
    public List<GreenRank> findAll() {
        final List<GreenRank> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_GR_GREEN_RANK;";
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                final GreenRank greenRank = new GreenRank(
                        resultSet.getLong("id_green_rank"),
                        resultSet.getDate("dt_connection"),
                        resultSet.getDate("dt_disconnection"),
                        resultSet.getLong("id_user"),
                        resultSet.getLong("id_eco_point")
                );
                all.add(greenRank);
            }
        } catch (SQLException e){
            logger.severe("No records found on Green Rank: " + e.getMessage());
        }
        return all;
    }

    @Override
    public GreenRank update(GreenRank greenRank, Connection connection) throws GreenRankNotFoundException, SQLException {
        final String sql = """
            UPDATE T_GR_GREEN_RANK SET DT_CONNECTION =?, DT_DISCONNECTION =?, ID_USER =?, ID_ECO_POINT =?
            WHERE ID_GREEN_RANK = ?;
            """;
        CallableStatement call = connection.prepareCall(sql);
        call.setObject(1, greenRank.getDateConnection());
        call.setObject(2, greenRank.getDateConnection());
        call.setObject(3, greenRank.getId());
        call.setObject(4, greenRank.getIdEcoPoint());
        call.registerOutParameter(5, OracleType.NUMBER);
        int linhasAlteradas = call.executeUpdate();
        long id = greenRank.getId();
        if(linhasAlteradas == 0 || id == 0) {
            throw new GreenRankNotFoundException();
        }
        return greenRank;
    }

    @Override
    public void deleteById(long id, Connection connection) throws GreenRankNotFoundException, SQLException {
        final String sql = "DELETE FROM T_GR_GREEN_RANK WHERE ID_GREEN_RANK = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new GreenRankNotFoundException();
        }

    }
}
