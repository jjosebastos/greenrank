package br.com.greenrank.service.address;

public class AddressServiceFactory {
    private AddressServiceFactory(){
        throw new UnsupportedOperationException();
    }

    public static AddressService create() {
        return new AddressServiceImpl();
    }
}
