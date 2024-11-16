package br.com.greenrank.model.wallet;

public class ConcreteWallet extends WalletFactory{
    @Override
    public Wallet createWallet() {
        return new Wallet();
    }
}
