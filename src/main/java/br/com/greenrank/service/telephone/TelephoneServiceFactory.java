package br.com.greenrank.service.telephone;

public class TelephoneServiceFactory {
    private TelephoneServiceFactory() {}
    public static TelephoneService create() {
        return new TelephoneServiceImpl();
    }
}
