package br.com.greenrank.service.telephone;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.telephone.TelephoneDao;
import br.com.greenrank.dao.telephone.TelephoneDaoFactory;
import br.com.greenrank.exceptions.TelephoneNotFoundException;
import br.com.greenrank.exceptions.TelephoneNotSavedException;
import br.com.greenrank.model.telephone.Telephone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {
    private final TelephoneDao dao = TelephoneDaoFactory.create();

    @Override
    public Telephone create(Telephone telephone) throws UnsupportedOperationException, SQLException, TelephoneNotSavedException {
        if(telephone.getId() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try{
                telephone = this.dao.save(telephone, connection);
                connection.commit();
                return telephone;
            } catch (SQLException | TelephoneNotSavedException e){
                connection.rollback();
                throw e;
            }

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<Telephone> getAll() {
        return this.dao.findAll();
    }

    @Override
    public Telephone update(Telephone telephone) throws SQLException, TelephoneNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        telephone = this.dao.update(telephone, connection);
        connection.commit();
        return telephone;
    }

    @Override
    public void deleteById(long id) throws TelephoneNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }
}
