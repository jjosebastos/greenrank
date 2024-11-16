package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDto {
    private Long id;
    private Double balance;
    private Long idUser;
}
