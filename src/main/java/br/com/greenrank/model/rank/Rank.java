package br.com.greenrank.model.rank;

import java.util.Objects;

public class Rank {
    private Long id;
    private int score;
    private Long idCustomer;
    private Long idEnterprise;

    public Rank(){}

    public Rank(Long id, int score, Long idCustomer, Long idEnterprise) {
        this.id = id;
        this.score = score;
        this.idCustomer = idCustomer;
        this.idEnterprise = idEnterprise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
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
        Rank rank = (Rank) o;
        return score == rank.score && Objects.equals(id, rank.id) && Objects.equals(idCustomer, rank.idCustomer) && Objects.equals(idEnterprise, rank.idEnterprise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, idCustomer, idEnterprise);
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", score=" + score +
                ", idCustomer=" + idCustomer +
                ", idEnterprise=" + idEnterprise +
                '}';
    }
}
