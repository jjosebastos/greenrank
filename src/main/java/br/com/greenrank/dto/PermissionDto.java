package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private Long idUser;
}
