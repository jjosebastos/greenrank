package br.com.greenrank.service.ecoDelivery;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.ecoDelivery.EcoDeliveryDao;
import br.com.greenrank.dao.ecoDelivery.EcoDeliveryDaoFactory;
import br.com.greenrank.exceptions.EcoDeliveryNotFoundException;
import br.com.greenrank.exceptions.EcoDeliveryNotSavedException;
import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoDelivery.EcoDelivery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EcoDeliveryServiceImpl implements EcoDeliveryService {
    private final EcoDeliveryDao dao = EcoDeliveryDaoFactory.create();
    @Override
    public EcoDelivery create(EcoDelivery ecoDelivery) throws UnsupportedOperationException, SQLException, EcoDeliveryNotSavedException {
        if(ecoDelivery.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try{
                ecoDelivery = this.dao.save(ecoDelivery, connection);
                connection.commit();
                return ecoDelivery;
            } catch (SQLException | EcoDeliveryNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<EcoDelivery> getAll() {
        return this.dao.findAll();
    }

    @Override
    public EcoDelivery update(EcoDelivery ecoDelivery) throws SQLException, EcoDeliveryNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        ecoDelivery = this.dao.update(ecoDelivery, connection);
        connection.commit();
        return ecoDelivery;
    }

    @Override
    public void deleteById(long id) throws SQLException, EcoDeliveryNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
