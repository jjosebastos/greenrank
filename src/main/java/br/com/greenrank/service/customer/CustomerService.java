package br.com.greenrank.service.customer;

import br.com.greenrank.exceptions.CustomerNotFoundException;
import br.com.greenrank.exceptions.CustomerNotSavedException;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.model.user.Customer;
import br.com.greenrank.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    Customer create(User user,Customer customer) throws UnsupportedOperationException, SQLException, CustomerNotSavedException;
    List<Customer> getAll();
    Customer update(User user, Customer customer) throws SQLException, UserNotFoundException, CustomerNotFoundException;
}
