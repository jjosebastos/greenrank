package br.com.greenrank.dao.wallet;

public class WalletDaoFactory {
    private WalletDaoFactory() {
        throw new UnsupportedOperationException();
    }
    public static WalletDao create(){
        return new WalletDaoImpl();
    }
}
