package br.com.greenrank.model.user;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer extends User implements IUser{
    private Long id;
    private String name;
    private String cpf;
    private String rg;
    private Date birthDate;
    private String gender;
    private Long idUser;

    @Override
    public void buildUser() {
        System.out.println("Building Customer user...");
    }

}
