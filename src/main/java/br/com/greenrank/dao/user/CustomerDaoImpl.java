package br.com.greenrank.dao.user;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.CustomerNotFoundException;
import br.com.greenrank.exceptions.CustomerNotSavedException;
import br.com.greenrank.model.user.Customer;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerDaoImpl implements CustomerDao {
    private final Logger logger = Logger.getLogger(CustomerDaoImpl.class.getName());
    @Override
    public Customer save(Customer customer, Connection connection) throws SQLException, CustomerNotSavedException {
        final String sql = """
                BEGIN
                    INSERT INTO T_GR_CUSTOMER (NM_CUSTOMER, NR_CPF, NR_RG, DT_BIRTH, DS_GENDER, ID_USER)
                    VALUES (?, ?, ?, ?, ?, ?)
                    RETURNING ID_CUSTOMER INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, customer.getName());
        call.setString(2, customer.getCpf());
        call.setString(3, customer.getRg());
        call.setObject(4, customer.getBirthDate(), Types.DATE);
        call.setString(5, customer.getGender());
        call.setObject(6, customer.getId());
        call.registerOutParameter(7, OracleType.NUMBER);

        int rowsAffected = call.executeUpdate();
        long id = call.getInt(7);
        if( rowsAffected == 0 || id == 0){
            throw new CustomerNotSavedException();
        }
        customer.setId(id);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        final String sql =
                """
                SELECT U.ID_USER, U.NM_USER, U.ID_EMAIL, U.VL_PASSWORD,
                C.ID_CUSTOMER, C.NM_CUSTOMER, C.NR_CPF, C.NR_RG, C.DT_BIRTH, C.DS_GENDER, C.ID_USER
                FROM T_GR_USER U
                INNER JOIN T_GR_CUSTOMER C ON C.ID_USER = U.ID_USER
                WHERE is_active = 'Y'
                """;
        final List<Customer> all = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer customer = new Customer(
                        rs.getLong("id_user"),
                        rs.getString("nm_user"),
                        rs.getString("vl_password"),
                        rs.getString("id_email"),
                        rs.getLong("id_customer"),
                        rs.getString("nm_customer"),
                        rs.getString("nr_cpf"),
                        rs.getString("nr_rg"),
                        rs.getDate("dt_birth"),
                        rs.getString("ds_gender"),
                        rs.getLong("id_user")
                );
                all.add(customer);
            }
        } catch (SQLException e) {
            logger.severe("No records found on Customer: " + e.getMessage());
        }

        return all;
    }

    @Override
    public Customer update(Customer customer, Connection connection) throws SQLException, CustomerNotFoundException {
        final String sql =
                """
                UPDATE T_GR_CUSTOMER 
                    SET NM_CUSTOMER = ?, NR_CPF=?, NR_RG=?, DT_BIRTH = ?, DS_GENDER = ?, ID_USER = ? 
                WHERE ID_CUSTOMER = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getCpf());
        ps.setString(3, customer.getRg());
        ps.setObject(4, customer.getBirthDate(), Types.DATE);
        ps.setString(5, customer.getGender());
        ps.setObject(6, customer.getIdUser());
        ps.setLong(7, customer.getId());
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected == 0){
            throw new CustomerNotFoundException();
        }
        return customer;
    }

}
