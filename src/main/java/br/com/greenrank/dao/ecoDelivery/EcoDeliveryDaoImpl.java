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
                    INSERT INTO T_GR_ECO_DELIVERY (TP_MATERIAL, DT_RECEIPT, QT_MATERIAL, ID_COSTUMER,
                    ID_ECO_POINT, ID_ENTERPRISE)
                    VALUES (?, ?, ?, ?, ?, ?)
                    RETURNING ID_COSTUMER INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, ecoDelivery.getTypeMaterial());
        call.setObject(2, ecoDelivery.getDateReceipt());
        call.setInt(3, ecoDelivery.getQuantity());
        call.setObject(4, ecoDelivery.getIdCustomer());
        call.setObject(5, ecoDelivery.getIdEcoPoint());
        call.setObject(6, ecoDelivery.getIdEnterprise());
        call.registerOutParameter(7, OracleType.NUMBER);

        int linesChanged = call.executeUpdate();
        long id = call.getLong(7);
        if(linesChanged == 0 || id == 0) {
            throw new EcoDeliveryNotSavedException();
        }
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
                        rs.getLong("id_eco_delivery"),
                        rs.getString("tp_material"),
                        rs.getDate("dt_receipt"),
                        rs.getInt("qt_material"),
                        rs.getLong("id_costumer"),
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
                UPDATE T_GR_ECO_DELIVERY (TP_MATERIAL, DT_RECEIPT, QT_MATERIAL,
                ID_COSTUMER, ID_ECO_POINT, ID_ENTERPRISE)
                WHERE ID_ECO_DELIVERY = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, ecoDelivery.getTypeMaterial());
        stmt.setObject(2, ecoDelivery.getDateReceipt());
        stmt.setInt(3, ecoDelivery.getQuantity());
        stmt.setObject(4, ecoDelivery.getIdCustomer());
        stmt.setObject(5, ecoDelivery.getIdEcoPoint());
        stmt.setObject(6, ecoDelivery.getIdEnterprise());

        int linesChanged = stmt.executeUpdate();
        if(linesChanged == 0) {
            throw new EcoDeliveryNotFoundException();
        }
        return null;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws EcoDeliveryNotFoundException, SQLException {
        final String sql = "DELETE FROM T_GR_ECO_DELIVERY WHERE ID_ECO_DELIVERY = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linesChanged = stmt.executeUpdate();
        if(linesChanged == 0) {
            throw new EcoDeliveryNotFoundException();
        }
    }
}
