package br.com.greenrank.service.ecoDelivery;

public class EcoDeliveryServiceFactory {
    private EcoDeliveryServiceFactory() {}
    public static EcoDeliveryService create() {
        return new EcoDeliveryServiceImpl();
    }
}
