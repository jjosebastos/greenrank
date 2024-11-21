package br.com.greenrank.service.rank;

public class RankServiceFactory {
    private RankServiceFactory() {}
    public static RankService create() {
        return new RankServiceImpl();
    }
}
