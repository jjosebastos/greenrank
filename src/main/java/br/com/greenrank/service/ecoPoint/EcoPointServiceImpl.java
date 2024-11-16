package br.com.greenrank.service.ecoPoint;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.ecoPoint.EcoPointDao;
import br.com.greenrank.dao.ecoPoint.EcoPointDaoFactory;
import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoPoint.EcoPoint;
import br.com.greenrank.model.ecoPoint.EcoPointFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EcoPointServiceImpl implements EcoPointService {
    private final EcoPointDao dao = EcoPointDaoFactory.create();
    @Override
    public EcoPoint create(EcoPoint ecoPoint) throws EcoPointNotSavedException, SQLException, UnsupportedOperationException {
        if(ecoPoint.getId() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                ecoPoint = this.dao.save(ecoPoint, connection);
                connection.commit();
                return ecoPoint;
            } catch (SQLException | EcoPointNotSavedException e) {
                connection.rollback();
                throw e;
            }

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<EcoPoint> listAll() {
        return this.dao.findAll();
    }

    @Override
    public EcoPoint update(EcoPoint ecoPoint) throws EcoPointNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        ecoPoint = this.dao.update(ecoPoint, connection);
        connection.commit();
        return ecoPoint;
    }

    @Override
    public void deleteById(Long id) throws EcoPointNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();

    }
}
