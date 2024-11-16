package br.com.greenrank.dao.ecoPoint;

public class EcoPointDaoFactory {
    private EcoPointDaoFactory() {}
    public static EcoPointDao create() {
        return new EcoPointDaoImpl();
    }
}
