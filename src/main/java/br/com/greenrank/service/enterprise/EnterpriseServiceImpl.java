package br.com.greenrank.service.enterprise;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.user.*;
import br.com.greenrank.exceptions.EnterpriseNotFoundException;
import br.com.greenrank.exceptions.EnterpriseNotSavedException;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.Enterprise;
import br.com.greenrank.model.user.User;

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
        if (user.getId() == null || enterprise.getId() == null) {
            throw new IllegalArgumentException("Os IDs de User e Enterprise não podem ser nulos para a atualização.");
        }

        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            // Atualiza o usuário
            user = this.userDao.update(user, connection);

            enterprise.setIdUser(user.getId());
            enterprise = this.enterpriseDao.update(enterprise, connection);
            connection.commit();

            return enterprise;

        } catch (SQLException | UserNotFoundException | EnterpriseNotFoundException e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(long id) throws SQLException, EnterpriseNotFoundException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            enterpriseDao.deleteUsersWithEnterprise(id, connection);
            connection.commit();
        };

    }
}
