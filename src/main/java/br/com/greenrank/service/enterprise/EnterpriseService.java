package br.com.greenrank.service.enterprise;

import br.com.greenrank.exceptions.EnterpriseNotFoundException;
import br.com.greenrank.exceptions.EnterpriseNotSavedException;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.model.user.Enterprise;
import br.com.greenrank.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface EnterpriseService {
    Enterprise create(User user, Enterprise enterprise) throws SQLException, UnsupportedOperationException, EnterpriseNotSavedException;
    List<Enterprise> getAll();
    Enterprise update(User user, Enterprise enterprise) throws SQLException, EnterpriseNotFoundException, UserNotFoundException;
    void deleteById(long id) throws SQLException, EnterpriseNotFoundException;
}
