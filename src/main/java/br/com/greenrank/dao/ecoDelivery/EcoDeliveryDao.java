package br.com.greenrank.dao.ecoDelivery;

import br.com.greenrank.exceptions.EcoDeliveryNotFoundException;
import br.com.greenrank.exceptions.EcoDeliveryNotSavedException;
import br.com.greenrank.model.ecoDelivery.EcoDelivery;
import br.com.greenrank.model.ecoDelivery.EcoDeliveryFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EcoDeliveryDao {
    EcoDelivery save(EcoDelivery ecoDelivery, Connection connection) throws EcoDeliveryNotSavedException, SQLException;
    List<EcoDelivery> findAll();
    EcoDelivery update(EcoDelivery ecoDelivery, Connection connection) throws EcoDeliveryNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws EcoDeliveryNotFoundException, SQLException;
    void updateRanking(Long idCustomer, Long idEnterprise, Connection connection) throws EcoDeliveryNotSavedException, SQLException;
}
