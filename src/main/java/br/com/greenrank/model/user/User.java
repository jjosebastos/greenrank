package br.com.greenrank.model.user;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String password;
    private String email;

}
