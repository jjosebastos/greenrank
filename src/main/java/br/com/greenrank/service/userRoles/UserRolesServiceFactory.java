package br.com.greenrank.service.userRoles;

public class UserRolesServiceFactory {
    private UserRolesServiceFactory(){}
    public static UserRolesService create(){
        return new UserRolesServiceImpl();
    }
}
