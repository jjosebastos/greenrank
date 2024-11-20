package br.com.greenrank.model.permission;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Permission {
    private Long id;
    private String name;
    private String description;
    private Long idUser;
}
