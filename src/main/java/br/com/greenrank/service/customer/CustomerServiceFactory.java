package br.com.greenrank.service.customer;

public class CustomerServiceFactory {
    private CustomerServiceFactory(){}
    public static CustomerService create() {
        return new CustomerServiceImpl();
    }
}
