package br.com.greenrank.model.userRoles;

import java.util.Objects;

public class UserRoles {
    private Long idUserRoles;
    private Long idUser;
    private Long idRole;

    public UserRoles() {
    }

    public UserRoles(Long idUserRoles, Long idUser, Long idRole) {
        this.idUserRoles = idUserRoles;
        this.idUser = idUser;
        this.idRole = idRole;
    }

    public Long getIdUserRoles() {
        return idUserRoles;
    }

    public void setIdUserRoles(Long idUserRoles) {
        this.idUserRoles = idUserRoles;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(idUserRoles, userRoles.idUserRoles) && Objects.equals(idUser, userRoles.idUser) && Objects.equals(idRole, userRoles.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserRoles, idUser, idRole);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "idUserRoles=" + idUserRoles +
                ", idUser=" + idUser +
                ", idRole=" + idRole +
                '}';
    }
}
