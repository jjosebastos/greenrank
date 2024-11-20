package br.com.greenrank.dao.ecoDelivery;

public class EcoDeliveryDaoFactory {
    private EcoDeliveryDaoFactory() {
    }
    public static EcoDeliveryDao create() {
        return new EcoDeliveryDaoImpl();
    }
}
