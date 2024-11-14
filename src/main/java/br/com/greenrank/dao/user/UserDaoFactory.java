package br.com.greenrank.dao.user;

public class UserDaoFactory {
    private UserDaoFactory() {
        throw new UnsupportedOperationException();
    }

    public static UserDao create() {
        return new UserDaoImpl();
    }
}
