package br.com.greenrank.service.roles;

public class RoleServiceFactory {
    private RoleServiceFactory() {}
    public static RoleService create() {
        return new RoleServiceImpl();
    }
}
