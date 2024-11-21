package br.com.greenrank.dao.ecoDelivery;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.EcoDeliveryNotFoundException;
import br.com.greenrank.exceptions.EcoDeliveryNotSavedException;
import br.com.greenrank.model.ecoDelivery.EcoDelivery;
import br.com.greenrank.model.ecoDelivery.EcoDeliveryFactory;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EcoDeliveryDaoImpl implements EcoDeliveryDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public EcoDelivery save(EcoDelivery ecoDelivery, Connection connection) throws EcoDeliveryNotSavedException, SQLException {
        final String sql = """
                BEGIN
                    INSERT INTO T_GR_ECO_DELIVERY (TP_MATERIAL, DT_RECEIPT, QT_MATERIAL, ID_CUSTOMER,
                    ID_ECO_POINT, ID_ENTERPRISE)
                    VALUES (?, ?, ?, ?, ?, ?)
                    RETURNING ID_DELIVERY INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, ecoDelivery.getTypeMaterial());
        call.setObject(2, ecoDelivery.getDateReceipt(), Types.DATE);
        call.setInt(3, ecoDelivery.getQuantity());
        call.setObject(4, ecoDelivery.getIdCustomer());
        call.setObject(5, ecoDelivery.getIdEcoPoint());
        call.setObject(6, ecoDelivery.getIdEnterprise());
        call.registerOutParameter(7, OracleType.NUMBER);

        int rowsAffected = call.executeUpdate();
        long id = call.getLong(7);
        if(rowsAffected == 0 || id == 0) {
            throw new EcoDeliveryNotSavedException();
        }

        updateRanking(ecoDelivery.getIdCustomer(), ecoDelivery.getIdEnterprise(), connection);
        connection.commit();

        ecoDelivery.setId(id);
        return ecoDelivery;
    }

    @Override
    public List<EcoDelivery> findAll(){
        final String sql = "SELECT * FROM T_GR_ECO_DELIVERY";
        final List<EcoDelivery> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EcoDelivery ecoDelivery = new EcoDelivery(
                        rs.getLong("id_delivery"),
                        rs.getString("tp_material"),
                        rs.getDate("dt_receipt"),
                        rs.getInt("qt_material"),
                        rs.getLong("id_customer"),
                        rs.getLong("id_eco_point"),
                        rs.getLong("id_enterprise")
                );
                all.add(ecoDelivery);
            }
        } catch (SQLException e) {
            logger.severe("No records found on Eco Delivery: " + e.getMessage());
        }
        return all;
    }

    @Override
    public EcoDelivery update(EcoDelivery ecoDelivery, Connection connection) throws EcoDeliveryNotFoundException, SQLException {
        final String sql = """
            UPDATE T_GR_ECO_DELIVERY 
            SET TP_MATERIAL = ?, DT_RECEIPT = ?, QT_MATERIAL = ?, 
                ID_CUSTOMER = ?, ID_ECO_POINT = ?, ID_ENTERPRISE = ? 
            WHERE ID_DELIVERY = ?
            """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, ecoDelivery.getTypeMaterial());
        stmt.setObject(2, ecoDelivery.getDateReceipt(), Types.DATE);
        stmt.setInt(3, ecoDelivery.getQuantity());
        stmt.setObject(4, ecoDelivery.getIdCustomer());
        stmt.setObject(5, ecoDelivery.getIdEcoPoint());
        stmt.setObject(6, ecoDelivery.getIdEnterprise());
        stmt.setLong(7, ecoDelivery.getId());

        int linesChanged = stmt.executeUpdate();
        if(linesChanged == 0) {
            throw new EcoDeliveryNotFoundException();
        }
        return ecoDelivery;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws EcoDeliveryNotFoundException, SQLException {
        final String sql = "DELETE FROM T_GR_ECO_DELIVERY WHERE ID_DELIVERY = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linesChanged = stmt.executeUpdate();
        if(linesChanged == 0) {
            throw new EcoDeliveryNotFoundException();
        }
    }

    public void updateRanking(Long idCustomer, Long idEnterprise, Connection connection) throws EcoDeliveryNotSavedException, SQLException {
        final String checkRankSql =
            """
            SELECT qt_score
            FROM T_GR_RANK
            WHERE (? IS NULL OR id_customer = ?)
            AND (? IS NULL OR id_enterprise = ?)
            """;

        boolean rankExists = false;

        try (PreparedStatement stmt = connection.prepareStatement(checkRankSql)) {
            stmt.setObject(1, idCustomer);  // Pode ser null, então usamos setObject
            stmt.setObject(2, idCustomer);  // Condição para verificar se existe para id_customer

            stmt.setObject(3, idEnterprise);  // Pode ser null, então usamos setObject
            stmt.setObject(4, idEnterprise);  // Condição para verificar se existe para id_enterprise

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rankExists = true;
            }
        }

        if (rankExists) {
            final String updateRankSql =
                    """
                    UPDATE T_GR_RANK
                    SET qt_score = qt_score + 5
                    WHERE (? IS NULL OR id_customer = ?)
                    AND (? IS NULL OR id_enterprise = ?)
                    """;

            try (PreparedStatement updateStmt = connection.prepareStatement(updateRankSql)) {
                updateStmt.setObject(1, idCustomer);
                updateStmt.setObject(2, idCustomer);
                updateStmt.setObject(3, idEnterprise);
                updateStmt.setObject(4, idEnterprise);
                updateStmt.executeUpdate();
            }
        } else {
            final String insertRankSql = "INSERT INTO T_GR_RANK (qt_score, id_customer, id_enterprise) VALUES (5, ?, ?)";

            try (PreparedStatement insertStmt = connection.prepareStatement(insertRankSql)) {
                insertStmt.setObject(1, idCustomer);
                insertStmt.setObject(2, idEnterprise);
                insertStmt.executeUpdate();
            }
        }
    }

}
