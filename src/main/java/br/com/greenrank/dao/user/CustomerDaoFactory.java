package br.com.greenrank.dao.user;

public class CustomerDaoFactory {
    private CustomerDaoFactory() {}
    public static CustomerDao create() {
        return new CustomerDaoImpl();
    }
}
