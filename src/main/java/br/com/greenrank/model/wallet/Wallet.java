package br.com.greenrank.model.wallet;

import lombok.*;

import javax.ws.rs.Path;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Wallet implements IWallet{
    private Long id;
    private Double balance;
    private Long idUser;

    @Override
    public void buildWallet() {
        System.out.println("Building wallet");
    }
}
