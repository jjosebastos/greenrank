package br.com.greenrank.model.address;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address implements IAddress{
    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
    private String cep;
    private Long idUser;
    private Long idEcoPoint;

    @Override
    public void buildAddress() {
        System.out.println("Building Address...");
    }
}
