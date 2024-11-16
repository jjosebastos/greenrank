package br.com.greenrank.service.wallet;

public class WalletServiceFactory {
    private WalletServiceFactory() {
        throw new UnsupportedOperationException();
    }
    public static WalletService create() {
        return new WalletServiceImpl();
    }
}
