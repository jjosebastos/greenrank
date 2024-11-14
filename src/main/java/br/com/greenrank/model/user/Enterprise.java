package br.com.greenrank.model.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Enterprise extends User implements IUser{

    private Long id;
    private String legalName;
    private String tradeName;
    private String cnpj;
    private String companyType;
    private Long idUser;

    @Override
    public void buildUser() {
        System.out.println("Building Enterprise user...");
    }
}
