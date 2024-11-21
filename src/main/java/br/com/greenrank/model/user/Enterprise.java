package br.com.greenrank.model.user;


import java.util.Objects;

public class Enterprise extends User implements IUser{

    private Long id;
    private String legalName;
    private String tradeName;
    private String cnpj;
    private String companyType;
    private Long idUser;

    public Enterprise() {}

    public Enterprise(Long idUserDad, String username, String password, String email, Long id, String legalName, String tradeName, String cnpj, String companyType, Long idUser) {
        super(idUserDad, username, password, email);
        this.id = id;
        this.legalName = legalName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.companyType = companyType;
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

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
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
        Enterprise that = (Enterprise) o;
        return Objects.equals(id, that.id) && Objects.equals(legalName, that.legalName) && Objects.equals(tradeName, that.tradeName) && Objects.equals(cnpj, that.cnpj) && Objects.equals(companyType, that.companyType) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, legalName, tradeName, cnpj, companyType, idUser);
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", legalName='" + legalName + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", companyType='" + companyType + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public void buildUser() {
        System.out.println("Building Enterprise user...");
    }
}
