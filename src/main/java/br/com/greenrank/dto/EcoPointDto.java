package br.com.greenrank.dto;


import java.util.Date;
public class EcoPointDto {
    private Long id;
    private String name;
    private String cnpj;
    private Date hourOpen;
    private Date hourClose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getHourOpen() {
        return hourOpen;
    }

    public void setHourOpen(Date hourOpen) {
        this.hourOpen = hourOpen;
    }

    public Date getHourClose() {
        return hourClose;
    }

    public void setHourClose(Date hourClose) {
        this.hourClose = hourClose;
    }
}
