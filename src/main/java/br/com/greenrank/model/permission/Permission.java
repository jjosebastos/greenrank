package br.com.greenrank.model.permission;

import java.util.Objects;

public class Permission {
    private Long id;
    private String name;
    private String description;
    private Long idUser;

    public Permission() {}

    public Permission(Long id, String name, String description, Long idUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idUser = idUser;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, idUser);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
