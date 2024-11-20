package br.com.greenrank.model.wallet;


import java.util.Objects;

public class Wallet implements IWallet{
    private Long id;
    private Double balance;
    private Long idUser;


    public Wallet() {}

    public Wallet(Long id, Double balance, Long idUser) {
        this.id = id;
        this.balance = balance;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(balance, wallet.balance) && Objects.equals(idUser, wallet.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, idUser);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public void buildWallet() {
        System.out.println("Building wallet");
    }
}
