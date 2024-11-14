package br.com.greenrank.service.address;

import br.com.greenrank.exceptions.AddressNotFoundException;
import br.com.greenrank.exceptions.AddressNotSavedException;
import br.com.greenrank.model.address.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {
    Address create(Address address) throws UnsupportedOperationException, AddressNotSavedException, SQLException;
    List<Address> findAll();
    Address update(Address address) throws AddressNotFoundException, SQLException;
    void deleteById(long id) throws AddressNotFoundException, SQLException;
}
