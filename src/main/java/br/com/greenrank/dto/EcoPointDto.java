package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EcoPointDto {
    private Long id;
    private String name;
    private String cnpj;
    private Date hourOpen;
    private Date hourClose;
}
