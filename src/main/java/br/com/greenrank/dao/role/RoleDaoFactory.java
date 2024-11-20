package br.com.greenrank.dao.role;

public class RoleDaoFactory {
    private RoleDaoFactory() {}
    public static RoleDao create() {
        return new RoleDaoImpl();
    }
}
