package br.com.greenrank.dao.permission;

public class PermissionDaoFactory {
    private PermissionDaoFactory() {}
    public static PermissionDao create() {
        return new PermissionDaoImpl();
    }
}
