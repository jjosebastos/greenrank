package br.com.greenrank.dao.address;

import br.com.greenrank.exceptions.AddressNotFoundException;
import br.com.greenrank.exceptions.AddressNotSavedException;
import br.com.greenrank.model.address.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    Address save(Address address, Connection connection) throws SQLException, AddressNotSavedException;
    List<Address> findAll();
    Address update(Address address, Connection connection) throws SQLException, AddressNotFoundException;
    void delete(long id, Connection connection) throws SQLException, AddressNotFoundException;
}
