package br.com.greenrank.service;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.user.UserDao;
import br.com.greenrank.dao.user.UserDaoFactory;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao dao = UserDaoFactory.create();
    @Override
    public User create(User user) throws SQLException, UserNotSavedException, UnsupportedOperationException {
        if(user.getId() == null){
            try {
                Connection connection = DatabaseConnectionFactory.create().get();
                user = this.dao.save(user, connection);
                connection.commit();
                return user;
            } catch (SQLException | UserNotSavedException e) {
                throw e;
            }
        } else {
            throw new UnsupportedOperationException("User ID should be null for new entries.");
        }
    }

    @Override
    public List<User> findAll() {
        return this.dao.findAll();
    }

    @Override
    public User update(User user) throws SQLException, UserNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        user = this.dao.update(user, connection);
        connection.commit();
        return user;
    }

    @Override
    public void delete(long id) throws SQLException, UserNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }
}
