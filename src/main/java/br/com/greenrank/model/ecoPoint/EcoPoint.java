package br.com.greenrank.model.ecoPoint;


import java.util.Date;
import java.util.Objects;

public class EcoPoint implements IEcoPoint {

    private Long id;
    private String name;
    private String cnpj;
    private Date hourOpen;
    private Date hourClose;

    public EcoPoint() {}

    public EcoPoint(Long id, String name, String cnpj, Date hourOpen, Date hourClose) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.hourOpen = hourOpen;
        this.hourClose = hourClose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcoPoint ecoPoint = (EcoPoint) o;
        return Objects.equals(id, ecoPoint.id) && Objects.equals(name, ecoPoint.name) && Objects.equals(cnpj, ecoPoint.cnpj) && Objects.equals(hourOpen, ecoPoint.hourOpen) && Objects.equals(hourClose, ecoPoint.hourClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj, hourOpen, hourClose);
    }

    @Override
    public String toString() {
        return "EcoPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", hourOpen=" + hourOpen +
                ", hourClose=" + hourClose +
                '}';
    }

    @Override
    public void buildEcoPoint() {
        System.out.println("Building EcoPoint...");
    }
}
