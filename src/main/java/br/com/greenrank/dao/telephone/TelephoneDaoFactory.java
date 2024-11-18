package br.com.greenrank.dao.telephone;

public class TelephoneDaoFactory {
    private TelephoneDaoFactory() {}
    public static TelephoneDao create() {
        return new TelephoneDaoImpl();
    }
}
