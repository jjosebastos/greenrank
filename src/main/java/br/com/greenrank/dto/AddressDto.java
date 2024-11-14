package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
    private String cep;
    private Long idUser;
    private Long idEcoPoint;
}
