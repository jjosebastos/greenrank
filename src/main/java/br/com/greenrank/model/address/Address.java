package br.com.greenrank.model.address;


import java.util.Objects;

public class Address implements IAddress{
    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
    private String cep;
    private Long idUser;
    private Long idEcoPoint;

    public Address() {}

    public Address(Long id, String street, String neighborhood, String city, String state, String complement, String cep, Long idUser, Long idEcoPoint) {
        this.id = id;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
        this.cep = cep;
        this.idUser = idUser;
        this.idEcoPoint = idEcoPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(neighborhood, address.neighborhood) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(complement, address.complement) && Objects.equals(cep, address.cep) && Objects.equals(idUser, address.idUser) && Objects.equals(idEcoPoint, address.idEcoPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, neighborhood, city, state, complement, cep, idUser, idEcoPoint);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", complement='" + complement + '\'' +
                ", cep='" + cep + '\'' +
                ", idUser=" + idUser +
                ", idEcoPoint=" + idEcoPoint +
                '}';
    }

    @Override
    public void buildAddress() {
        System.out.println("Building Address...");
    }
}
