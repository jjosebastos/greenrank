package br.com.greenrank.dao.address;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.AddressNotFoundException;
import br.com.greenrank.exceptions.AddressNotSavedException;
import br.com.greenrank.model.address.Address;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AddressDaoImpl implements AddressDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Address save(Address address, Connection connection) throws SQLException, AddressNotSavedException {
        final String sql = """
                BEGIN 
                    INSERT INTO T_GR_ADDRESS (NM_STREET,NR_ADDRESS,NM_NEIGHBORHOOD,NM_CITY,NM_STATE,DS_COMPLEMENT,NR_CEP,ID_USER,ID_ECO_POINT)
                    VALUES (?,?,?,?,?,?,?,?,?) RETURNING ID_ADDRESS INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, address.getStreet());
        call.setString(2, address.getNumber());
        call.setString(3, address.getNeighborhood());
        call.setString(4, address.getCity());
        call.setString(5, address.getState());
        call.setString(6, address.getComplement());
        call.setString(7, address.getCep());
        call.setObject(8, address.getIdUser());
        call.setObject(9, address.getIdEcoPoint());
        call.registerOutParameter(10 , OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(10);
        if (linhasAlteradas == 0 || id == 0 ) {
            throw new AddressNotSavedException();
        }
        address.setId(id);
        return address;
    }

    @Override
    public List<Address> findAll() {
        final String sql = "SELECT * FROM T_GR_ADDRESS";
        final List<Address> all = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Address address = new Address(
                        rs.getLong("id_address"),
                        rs.getString("nm_street"),
                        rs.getString("nr_address"),
                        rs.getString("nm_neighborhood"),
                        rs.getString("nm_city"),
                        rs.getString("nm_state"),
                        rs.getString("ds_complement"),
                        rs.getString("nr_cep"),
                        rs.getLong("id_user"),
                        rs.getLong("id_eco_point")
                );
                all.add(address);
            }

        } catch (SQLException e){
            logger.severe("No registration found on address");
        }
        return all;
    }

    @Override
    public Address update(Address address, Connection connection) throws SQLException, AddressNotFoundException {
        final String sql = """
                UPDATE T_GR_ADDRESS SET NM_STREET=?,NR_ADDRESS=?,NM_NEIGHBORHOOD=?,NM_CITY=?,NM_STATE=?,DS_COMPLEMENT=?,NR_CEP=?,ID_USER=?,ID_ECO_POINT=?
                WHERE ID_ADDRESS=?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, address.getStreet());
        stmt.setString(2, address.getNumber());
        stmt.setString(3, address.getNeighborhood());
        stmt.setString(4, address.getCity());
        stmt.setString(5, address.getState());
        stmt.setString(6, address.getComplement());
        stmt.setString(7, address.getCep());
        stmt.setObject(8, address.getIdUser());
        stmt.setObject(9, address.getIdEcoPoint());
        stmt.setLong(10, address.getId());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new AddressNotFoundException();
        }
        return address;
    }

    @Override
    public void delete(long id, Connection connection) throws SQLException, AddressNotFoundException {
        final String sql = "DELETE FROM T_GR_ADDRESS WHERE ID_ADDRESS = ?";
        CallableStatement call = connection.prepareCall(sql);
        call.setLong(1, id);
        int linhasAlteradas = call.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new AddressNotFoundException();
        }


    }
}
