package br.com.greenrank.service.enterprise;

public class EnterpriseServiceFactory {
    private EnterpriseServiceFactory() {}
    public static EnterpriseService create() {
        return new EnterpriseServiceImpl();
    }
}
