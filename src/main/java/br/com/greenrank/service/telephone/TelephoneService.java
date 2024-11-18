package br.com.greenrank.service.telephone;

import br.com.greenrank.exceptions.TelephoneNotFoundException;
import br.com.greenrank.exceptions.TelephoneNotSavedException;
import br.com.greenrank.model.telephone.Telephone;

import java.sql.SQLException;
import java.util.List;

public interface TelephoneService {
    Telephone create(Telephone telephone) throws UnsupportedOperationException, SQLException, TelephoneNotSavedException;
    List<Telephone> getAll();
    Telephone update(Telephone telephone) throws SQLException, TelephoneNotFoundException;
    void deleteById(long id) throws TelephoneNotFoundException, SQLException;
}
