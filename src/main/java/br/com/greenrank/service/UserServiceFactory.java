package br.com.greenrank.service;

public class UserServiceFactory {
    private UserServiceFactory() {
        throw new UnsupportedOperationException();
    }

    public static UserService create() {
        return new UserServiceImpl();
    }
}
