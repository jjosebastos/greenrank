package br.com.greenrank.model.telephone;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Telephone implements ITelephone{
    private Long id;
    private String ddd;
    private String number;
    private String type;
    private Long idEcoPoint;
    private Long idUser;

    @Override
    public void buildTelephone() {
        System.out.println("Building telephone...");
    }
}
