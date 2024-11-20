package br.com.greenrank.model.ecoDelivery;

public class EcoDeliveryConcrete extends EcoDeliveryFactory {
    @Override
    public EcoDelivery newEcoDelivery() {
        return new EcoDelivery();
    }
}
