package br.com.greenrank.dao.user;

import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User save(User user, Connection connection) throws UserNotSavedException, SQLException;
    List<User> findAll();
    User update(User user, Connection connection) throws UserNotFoundException, SQLException;
    void delete(Long id, Connection connection) throws UserNotFoundException, SQLException;
}
