package br.com.greenrank.model.ecoDelivery;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EcoDelivery implements IEcoDelivery {
    private Long id;
    private String typeMaterial;
    private Date dateReceipt;
    private int quantity;
    private Long idCustomer;
    private Long idEcoPoint;
    private Long idEnterprise;

    @Override
    public void Build() {
        System.out.println("Building EcoDelivery...");
    }
}
