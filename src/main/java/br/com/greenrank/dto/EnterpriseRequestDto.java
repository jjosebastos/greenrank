package br.com.greenrank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnterpriseRequestDto {

    @JsonProperty("idDad")
    private Long idDad;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("idEmail")
    private String idEmail;
    @JsonProperty("idEnterprise")
    private Long idEnterprise;
    @JsonProperty("legalName")
    private String legalName;
    @JsonProperty("tradeName")
    private String tradeName;
    @JsonProperty("cnpj")
    private String cnpj;
    @JsonProperty("companyType")
    private String companyType;
    @JsonProperty("idUser")
    private Long idUser;

    public Long getIdDad() {
        return idDad;
    }

    public void setIdDad(Long idDad) {
        this.idDad = idDad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(String idEmail) {
        this.idEmail = idEmail;
    }

    public Long getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(Long idEnterprise) {
        this.idEnterprise = idEnterprise;
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
}