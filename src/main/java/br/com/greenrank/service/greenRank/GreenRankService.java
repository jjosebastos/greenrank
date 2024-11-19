package br.com.greenrank.service.greenRank;

import br.com.greenrank.exceptions.GreenRankNotFoundException;
import br.com.greenrank.exceptions.GreenRankNotSavedException;
import br.com.greenrank.model.greenRank.GreenRank;

import java.sql.SQLException;
import java.util.List;

public interface GreenRankService {
    GreenRank create(GreenRank greenRank) throws UnsupportedOperationException, SQLException, GreenRankNotSavedException;
    List<GreenRank> getAll();
    GreenRank update(GreenRank greenRank) throws SQLException, GreenRankNotFoundException;
    void deleteById(long id) throws SQLException, GreenRankNotFoundException ;
}
