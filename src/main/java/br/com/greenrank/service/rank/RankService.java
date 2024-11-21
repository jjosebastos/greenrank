package br.com.greenrank.service.rank;

import br.com.greenrank.model.rank.Rank;

import java.util.List;

public interface RankService {
    List<Rank> getAll();
    List<Rank> getTopRanks();
}
