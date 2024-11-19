package br.com.greenrank.service.greenRank;

public class GreenRankServiceFactory {
    private GreenRankServiceFactory() {}
    public static GreenRankService create(){
        return new GreenRankServiceImpl();
    }
}
