package br.com.greenrank.dao.rank;

import br.com.greenrank.model.rank.Rank;

import java.util.List;

public interface RankDao {
    List<Rank> listAll();
    List<Rank> listTopPontuations();
}
