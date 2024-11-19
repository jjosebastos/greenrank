package br.com.greenrank.dao.user;

public class EnterpriseDaoFactory {
    public EnterpriseDaoFactory() {}
    public static EnterpriseDao create() {
        return new EnterpriseDaoImpl();
    }
}
