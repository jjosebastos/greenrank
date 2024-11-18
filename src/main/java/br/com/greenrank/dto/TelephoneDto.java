package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelephoneDto {
    private Long id;
    private String ddd;
    private String number;
    private String type;
    private Long idEcoPoint;
    private Long idUser;

}
