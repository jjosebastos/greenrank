package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterpriseDto {
    private Long id;
    private String legalName;
    private String tradeName;
    private String cnpj;
    private String companyType;
    private Long idUser;
}
