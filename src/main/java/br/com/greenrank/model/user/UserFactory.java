package br.com.greenrank.model.user;

public class UserFactory {

    public static User createUser(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "customer":
                return new Customer();
            case "enterprise":
                return new Enterprise();
            default:
                return null;
        }
    }

}
