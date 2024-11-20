package br.com.greenrank.model.greenRank;

import java.util.Date;
import java.util.Objects;

public class GreenRank {
    private Long id;
    private Date dateConnection;
    private Date dateDisconection;
    private Long idUser;
    private Long idEcoPoint;

    public GreenRank() {}

    public GreenRank(Long id, Date dateConnection, Date dateDisconection, Long idUser, Long idEcoPoint) {
        this.id = id;
        this.dateConnection = dateConnection;
        this.dateDisconection = dateDisconection;
        this.idUser = idUser;
        this.idEcoPoint = idEcoPoint;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreenRank greenRank = (GreenRank) o;
        return Objects.equals(id, greenRank.id) && Objects.equals(dateConnection, greenRank.dateConnection) && Objects.equals(dateDisconection, greenRank.dateDisconection) && Objects.equals(idUser, greenRank.idUser) && Objects.equals(idEcoPoint, greenRank.idEcoPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateConnection, dateDisconection, idUser, idEcoPoint);
    }

    @Override
    public String toString() {
        return "GreenRank{" +
                "id=" + id +
                ", dateConnection=" + dateConnection +
                ", dateDisconection=" + dateDisconection +
                ", idUser=" + idUser +
                ", idEcoPoint=" + idEcoPoint +
                '}';
    }
}
