package br.com.greenrank.dto;

import java.util.Date;

public class GreenRankDto {
    private Long id;
    private Date dateConnection;
    private Date dateDisconection;
    private Long idUser;
    private Long idEcoPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }

    public Date getDateDisconection() {
        return dateDisconection;
    }

    public void setDateDisconection(Date dateDisconection) {
        this.dateDisconection = dateDisconection;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdEcoPoint() {
        return idEcoPoint;
    }

    public void setIdEcoPoint(Long idEcoPoint) {
        this.idEcoPoint = idEcoPoint;
    }

}
