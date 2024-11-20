package br.com.greenrank.service.permission;

public class PermissionServiceFactory {
    private PermissionServiceFactory() {}
    public static PermissionService create() {
        return new PermissionServiceImpl();
    }
}
