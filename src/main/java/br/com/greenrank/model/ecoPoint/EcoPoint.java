package br.com.greenrank.model.ecoPoint;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EcoPoint implements IEcoPoint {

    private Long id;
    private String name;
    private String cnpj;
    private Date hourOpen;
    private Date hourClose;

    @Override
    public void buildEcoPoint() {
        System.out.println("Building EcoPoint...");
    }
}
