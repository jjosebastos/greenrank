package br.com.greenrank.dao.greenRank;

public class GreenRankDaoFactory {
    private GreenRankDaoFactory() {}
    public static GreenRankDao create() {
        return new GreenRankDaoImpl();
    }
}
