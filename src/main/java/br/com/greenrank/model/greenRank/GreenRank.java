package br.com.greenrank.model.greenRank;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GreenRank {
    private Long id;
    private Date dateConnection;
    private Date dateDisconection;
    private Long idUser;
    private Long idEcoPoint;
}
