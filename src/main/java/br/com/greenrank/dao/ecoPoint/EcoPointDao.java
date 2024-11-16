package br.com.greenrank.dao.ecoPoint;

import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoPoint.EcoPoint;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EcoPointDao {
    EcoPoint save(EcoPoint ecoPoint, Connection connection) throws SQLException, EcoPointNotSavedException;
    List<EcoPoint> findAll();
    EcoPoint update(EcoPoint ecoPoint, Connection connection) throws SQLException, EcoPointNotFoundException;
    void deleteById(long id, Connection connection) throws SQLException, EcoPointNotFoundException;
}
