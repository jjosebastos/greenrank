package br.com.greenrank.service.ecoDelivery;

import br.com.greenrank.exceptions.EcoDeliveryNotFoundException;
import br.com.greenrank.exceptions.EcoDeliveryNotSavedException;
import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoDelivery.EcoDelivery;

import java.sql.SQLException;
import java.util.List;

public interface EcoDeliveryService {
    EcoDelivery create(EcoDelivery ecoDelivery) throws UnsupportedOperationException, SQLException, EcoDeliveryNotSavedException;
    List<EcoDelivery> getAll();
    EcoDelivery update(EcoDelivery ecoDelivery) throws SQLException, EcoDeliveryNotFoundException;
    void deleteById(long id) throws SQLException, EcoDeliveryNotFoundException;
}
