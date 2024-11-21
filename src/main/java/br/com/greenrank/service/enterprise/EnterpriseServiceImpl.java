package br.com.greenrank.service.enterprise;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.user.*;
import br.com.greenrank.exceptions.*;
import br.com.greenrank.model.user.Enterprise;
import br.com.greenrank.model.user.User;

import java.lang.UnsupportedOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseServiceImpl implements EnterpriseService {
    private final UserDao userDao = UserDaoFactory.create();
    private final EnterpriseDao enterpriseDao = EnterpriseDaoFactory.create();
    @Override
    public Enterprise create(User user, Enterprise enterprise) throws SQLException, UnsupportedOperationException, EnterpriseNotSavedException {
        if (user.getId() != null) {
            throw new UnsupportedOperationException("User ID must be null to create a new user and associated enterprise.");
        }

        try (Connection connection = DatabaseConnectionFactory.create().get()) {
                try {
                    user = this.userDao.save(user, connection);
                    enterprise.setIdUser(user.getId());
                    enterprise = enterpriseDao.save(enterprise, connection);
                    connection.commit();
                    return enterprise;
                } catch (SQLException | UserNotSavedException | EnterpriseNotSavedException e) {
                    connection.rollback();
                    throw new SQLException("Failed to create enterprise and user. Transaction rolled back.", e);
                }
            }
    }

    @Override
    public List<Enterprise> getAll() {
        return this.enterpriseDao.findAll();
    }

    @Override
    public Enterprise update(User user, Enterprise enterprise) throws SQLException, UserNotFoundException, EnterpriseNotFoundException {

        final String sql = "SELECT COUNT(*) FROM T_GR_USER WHERE ID_USER = ?";
        try(Connection connection = DatabaseConnectionFactory.create().get();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, user.getId());
            int rowsAfected = statement.executeUpdate();
            if(rowsAfected == 0){
                throw new EnterpriseNotFoundException();
            }
        }

        if (user.getId() == null || enterprise.getId() == null) {
            throw new IllegalArgumentException("Os IDs de User e Enterprise não podem ser nulos para a atualização.");
        }

        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            user = this.userDao.update(user, connection);
            enterprise = this.enterpriseDao.update(enterprise, connection);
            connection.commit();
            return enterprise;

        } catch (SQLException | UserNotFoundException | EnterpriseNotFoundException e) {
            connection.rollback();
            throw e;
        }
    }

}
