package br.com.greenrank.model.user;


import java.util.Date;
import java.util.Objects;


public class Customer extends User implements IUser{
    private Long id;
    private String name;
    private String cpf;
    private String rg;
    private Date birthDate;
    private String gender;
    private Long idUser;

    public Customer() {
    }

    public Customer(Long idUser, String username, String password, String idEmail, Long id, String name, String cpf, String rg, Date birthDate, String gender) {
        super(idUser, username, password, idEmail);
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.birthDate = birthDate;
        this.gender = gender;
        this.idUser = idUser;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(cpf, customer.cpf) && Objects.equals(rg, customer.rg) && Objects.equals(birthDate, customer.birthDate) && Objects.equals(gender, customer.gender) && Objects.equals(idUser, customer.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, cpf, rg, birthDate, gender, idUser);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public void buildUser() {
        System.out.println("Building Customer user...");
    }

}
