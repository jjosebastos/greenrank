package br.com.greenrank.service;

import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User create(User user) throws SQLException, UserNotSavedException, UnsupportedOperationException;
    List<User> findAll();
    User update(User user) throws SQLException, UserNotFoundException;
    void delete(long id) throws SQLException, UserNotFoundException;
}
