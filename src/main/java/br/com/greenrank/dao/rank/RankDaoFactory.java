package br.com.greenrank.dao.rank;

public class RankDaoFactory {
    private RankDaoFactory() {}
    public static RankDao create() {
        return new RankDaoImpl();
    }
}
