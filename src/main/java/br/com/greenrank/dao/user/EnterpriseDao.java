package br.com.greenrank.dao.user;

import br.com.greenrank.exceptions.EnterpriseNotFoundException;
import br.com.greenrank.exceptions.EnterpriseNotSavedException;
import br.com.greenrank.model.user.Enterprise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EnterpriseDao {
    Enterprise save(Enterprise enterprise, Connection connection) throws SQLException, EnterpriseNotSavedException;
    List<Enterprise> findAll();
    Enterprise update(Enterprise enterprise, Connection connection) throws SQLException, EnterpriseNotFoundException;
    void deleteUsersWithEnterprise(long idUser, Connection connection) throws SQLException, EnterpriseNotFoundException;
}
