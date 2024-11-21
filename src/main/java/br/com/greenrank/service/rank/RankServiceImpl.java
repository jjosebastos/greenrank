package br.com.greenrank.service.rank;

import br.com.greenrank.dao.rank.RankDao;
import br.com.greenrank.dao.rank.RankDaoFactory;
import br.com.greenrank.model.rank.Rank;

import java.util.List;

public class RankServiceImpl implements RankService {

    private final RankDao dao = RankDaoFactory.create();

    @Override
    public List<Rank> getAll() {
        return this.dao.listAll();
    }

    @Override
    public List<Rank> getTopRanks() {
        return this.dao.listTopPontuations();
    }
}
