package br.com.greenrank.dao.user;

import br.com.greenrank.exceptions.CustomerNotFoundException;
import br.com.greenrank.exceptions.CustomerNotSavedException;
import br.com.greenrank.model.user.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer, Connection connection) throws SQLException, CustomerNotSavedException;
    List<Customer> findAll();
    Customer update(Customer customer, Connection connection) throws SQLException, CustomerNotFoundException;
}
