package br.com.greenrank.dto;

public class UserRolesDto {
    private Long idUserRoles;
    private Long idUser;
    private Long idRole;

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
}
