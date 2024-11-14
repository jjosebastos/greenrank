package br.com.greenrank.service.address;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.address.AddressDao;
import br.com.greenrank.dao.address.AddressDaoFactory;
import br.com.greenrank.exceptions.AddressNotFoundException;
import br.com.greenrank.exceptions.AddressNotSavedException;
import br.com.greenrank.model.address.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressDao dao = AddressDaoFactory.create();

    @Override
    public Address create(Address address) throws UnsupportedOperationException, AddressNotSavedException, SQLException {
        if(address.getId() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                address = this.dao.save(address, connection);
                connection.commit();
                return address;
            } catch (SQLException | AddressNotSavedException e ) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public List<Address> findAll() {
        return this.dao.findAll();
    }

    @Override
    public Address update(Address address) throws AddressNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        address = this.dao.update(address, connection);
        connection.commit();
        return address;
    }

    @Override
    public void deleteById(long id) throws AddressNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }
}
