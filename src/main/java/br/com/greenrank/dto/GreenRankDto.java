package br.com.greenrank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GreenRankDto {
    private Long id;
    private Date dateConnection;
    private Date dateDisconection;
    private Long idUser;
    private Long idEcoPoint;
}
