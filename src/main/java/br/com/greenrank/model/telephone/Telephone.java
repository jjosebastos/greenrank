package br.com.greenrank.model.telephone;


import java.util.Objects;

public class Telephone implements ITelephone{
    private Long id;
    private String ddd;
    private String number;
    private String type;
    private Long idEcoPoint;
    private Long idUser;

    public Telephone() {}

    public Telephone(Long id, String ddd, String number, String type, Long idEcoPoint, Long idUser) {
        this.id = id;
        this.ddd = ddd;
        this.number = number;
        this.type = type;
        this.idEcoPoint = idEcoPoint;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdEcoPoint() {
        return idEcoPoint;
    }

    public void setIdEcoPoint(Long idEcoPoint) {
        this.idEcoPoint = idEcoPoint;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return Objects.equals(id, telephone.id) && Objects.equals(ddd, telephone.ddd) && Objects.equals(number, telephone.number) && Objects.equals(type, telephone.type) && Objects.equals(idEcoPoint, telephone.idEcoPoint) && Objects.equals(idUser, telephone.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ddd, number, type, idEcoPoint, idUser);
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "id=" + id +
                ", ddd='" + ddd + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", idEcoPoint=" + idEcoPoint +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public void buildTelephone() {
        System.out.println("Building telephone...");
    }
}
