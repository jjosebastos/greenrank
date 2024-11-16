package br.com.greenrank.model.address;

public class ConcreteAddress extends AddressFactory{

    @Override
    public Address createAddress() {
        return new Address();
    }
}
