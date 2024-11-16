package br.com.greenrank.service.ecoPoint;

public class EcoPointServiceFactory {
    private EcoPointServiceFactory() {}
    public static EcoPointService create(){
        return new EcoPointServiceImpl();
    }
}
