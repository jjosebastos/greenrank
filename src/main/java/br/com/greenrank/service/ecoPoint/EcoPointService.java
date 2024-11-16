package br.com.greenrank.service.ecoPoint;

import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoPoint.EcoPoint;

import java.sql.SQLException;
import java.util.List;

public interface EcoPointService {
    EcoPoint create(EcoPoint ecoPoint) throws EcoPointNotSavedException, SQLException, UnsupportedOperationException;
    List<EcoPoint> listAll();
    EcoPoint update(EcoPoint ecoPoint) throws EcoPointNotFoundException, SQLException;
    void deleteById(Long id) throws EcoPointNotFoundException, SQLException;
}
