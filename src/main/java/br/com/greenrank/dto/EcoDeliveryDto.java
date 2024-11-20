package br.com.greenrank.dto;

import java.util.Date;

public class EcoDeliveryDto {
    private Long id;
    private String typeMaterial;
    private Date dateReceipt;
    private int quantity;
    private Long idCustomer;
    private Long idEcoPoint;
    private Long idEnterprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial) {
        this.typeMaterial = typeMaterial;
    }

    public Date getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(Date dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdEcoPoint() {
        return idEcoPoint;
    }

    public void setIdEcoPoint(Long idEcoPoint) {
        this.idEcoPoint = idEcoPoint;
    }

    public Long getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(Long idEnterprise) {
        this.idEnterprise = idEnterprise;
    }
}
