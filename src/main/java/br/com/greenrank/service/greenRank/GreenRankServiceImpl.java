package br.com.greenrank.service.greenRank;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.greenRank.GreenRankDao;
import br.com.greenrank.dao.greenRank.GreenRankDaoFactory;
import br.com.greenrank.exceptions.GreenRankNotFoundException;
import br.com.greenrank.exceptions.GreenRankNotSavedException;
import br.com.greenrank.model.greenRank.GreenRank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GreenRankServiceImpl implements GreenRankService {

    private final GreenRankDao dao = GreenRankDaoFactory.create();

    @Override
    public GreenRank create(GreenRank greenRank) throws UnsupportedOperationException, SQLException, GreenRankNotSavedException {
        if(greenRank.getId() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                greenRank = this.dao.save(greenRank, connection);
                connection.commit();
                return greenRank;
            } catch (SQLException | GreenRankNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<GreenRank> getAll() {
        return this.dao.findAll();
    }

    @Override
    public GreenRank update(GreenRank greenRank) throws SQLException, GreenRankNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        greenRank = this.dao.update(greenRank, connection);
        connection.commit();
        return greenRank;
    }

    @Override
    public void deleteById(long id) throws SQLException, GreenRankNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
