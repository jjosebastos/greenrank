package br.com.greenrank.dao.telephone;

import br.com.greenrank.exceptions.TelephoneNotFoundException;
import br.com.greenrank.exceptions.TelephoneNotSavedException;
import br.com.greenrank.model.telephone.Telephone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TelephoneDao {
    Telephone save(Telephone telephone, Connection connection) throws TelephoneNotSavedException, SQLException;
    List<Telephone> findAll();
    Telephone update(Telephone telephone, Connection connection) throws TelephoneNotFoundException, SQLException;
    void delete(long id, Connection connection) throws TelephoneNotFoundException, SQLException;
}
