package br.com.greenrank.model.ecoDelivery;


import java.util.Date;
import java.util.Objects;

public class EcoDelivery implements IEcoDelivery {
    private Long id;
    private String typeMaterial;
    private Date dateReceipt;
    private int quantity;
    private Long idCustomer;
    private Long idEcoPoint;
    private Long idEnterprise;

    public EcoDelivery() {}

    public EcoDelivery(Long id, String typeMaterial, Date dateReceipt, int quantity, Long idCustomer, Long idEcoPoint, Long idEnterprise) {
        this.id = id;
        this.typeMaterial = typeMaterial;
        this.dateReceipt = dateReceipt;
        this.quantity = quantity;
        this.idCustomer = idCustomer;
        this.idEcoPoint = idEcoPoint;
        this.idEnterprise = idEnterprise;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcoDelivery that = (EcoDelivery) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(typeMaterial, that.typeMaterial) && Objects.equals(dateReceipt, that.dateReceipt) && Objects.equals(idCustomer, that.idCustomer) && Objects.equals(idEcoPoint, that.idEcoPoint) && Objects.equals(idEnterprise, that.idEnterprise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeMaterial, dateReceipt, quantity, idCustomer, idEcoPoint, idEnterprise);
    }

    @Override
    public String toString() {
        return "EcoDelivery{" +
                "id=" + id +
                ", typeMaterial='" + typeMaterial + '\'' +
                ", dateReceipt=" + dateReceipt +
                ", quantity=" + quantity +
                ", idCustomer=" + idCustomer +
                ", idEcoPoint=" + idEcoPoint +
                ", idEnterprise=" + idEnterprise +
                '}';
    }

    @Override
    public void Build() {
        System.out.println("Building EcoDelivery...");
    }
}
