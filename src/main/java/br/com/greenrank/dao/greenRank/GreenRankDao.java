package br.com.greenrank.dao.greenRank;

import br.com.greenrank.exceptions.GreenRankNotFoundException;
import br.com.greenrank.exceptions.GreenRankNotSavedException;
import br.com.greenrank.model.greenRank.GreenRank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GreenRankDao {
    GreenRank save(GreenRank greenRank, Connection connection) throws GreenRankNotSavedException, SQLException;
    List<GreenRank> findAll();
    GreenRank update(GreenRank greenRank, Connection connection) throws GreenRankNotFoundException, SQLException;
    void deleteById(long id, Connection connection) throws GreenRankNotFoundException, SQLException;
}
