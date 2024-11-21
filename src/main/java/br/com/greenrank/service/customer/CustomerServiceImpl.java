package br.com.greenrank.service.customer;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.user.CustomerDao;
import br.com.greenrank.dao.user.CustomerDaoFactory;
import br.com.greenrank.dao.user.UserDao;
import br.com.greenrank.dao.user.UserDaoFactory;
import br.com.greenrank.exceptions.*;
import br.com.greenrank.model.user.Customer;
import br.com.greenrank.model.user.User;

import java.lang.UnsupportedOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final UserDao userDao = UserDaoFactory.create();
    private final CustomerDao customerDao = CustomerDaoFactory.create();
    @Override
    public Customer create(User user, Customer customer) throws UnsupportedOperationException, SQLException, CustomerNotSavedException {
        if(user.getId() != null || customer.getId() != null) {
            throw new UnsupportedOperationException();
        }

        try(Connection connection = DatabaseConnectionFactory.create().get()){
            try {
                user = this.userDao.save(user, connection);
                customer.setId(user.getId());
                customerDao.save(customer, connection);
                connection.commit();
                return customer;
            } catch (SQLException | UserNotSavedException | CustomerNotSavedException e){
                connection.rollback();
                throw new SQLException("Failed to create enterprise and user. Transaction rolled back.");
            }
        }
    }

    @Override
    public List<Customer> getAll() {
        return this.customerDao.findAll();
    }

    @Override
    public Customer update(User user, Customer customer) throws SQLException, CustomerNotFoundException {
        final String sql = "SELECT COUNT(*) FROM T_GR_USER WHERE ID_USER = ?";
        try {
            Connection connection = DatabaseConnectionFactory.create().get();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getId());
            int rowsAfected = statement.executeUpdate();
            if(rowsAfected == 0){
                throw new CustomerNotFoundException();
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (user.getId() == null || customer.getId() == null) {
            throw new IllegalArgumentException("Os IDs de User e Enterprise não podem ser nulos para a atualização.");
        }

        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            user = this.userDao.update(user, connection);
            customer = this.customerDao.update(customer, connection);
            connection.commit();
            return customer;

        } catch (SQLException | UserNotFoundException | CustomerNotFoundException e) {
            connection.rollback();
            throw new SQLException("Failed to update enterprise and user. Transaction rolled back.");
        }
    }
}
