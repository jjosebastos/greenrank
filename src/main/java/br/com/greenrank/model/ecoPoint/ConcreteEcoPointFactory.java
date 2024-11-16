package br.com.greenrank.model.ecoPoint;

public class ConcreteEcoPointFactory extends EcoPointFactory {

    @Override
    public EcoPoint createEcoPoint() {
        return new EcoPoint();
    }
}
