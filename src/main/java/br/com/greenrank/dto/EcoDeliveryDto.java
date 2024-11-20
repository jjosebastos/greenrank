package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EcoDeliveryDto {
    private Long id;
    private String typeMaterial;
    private Date dateReceipt;
    private int quantity;
    private Long idCustomer;
    private Long idEcoPoint;
    private Long idEnterprise;
}
