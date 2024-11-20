package br.com.greenrank.model.roles;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Roles {
    private Long id;
    private String name;
    private String description;

}
