package br.com.greenrank.dao.address;

public class AddressDaoFactory {
    private AddressDaoFactory() {
        throw new UnsupportedOperationException();
    }
    public static AddressDao create() {
        return new AddressDaoImpl();
    }
}
