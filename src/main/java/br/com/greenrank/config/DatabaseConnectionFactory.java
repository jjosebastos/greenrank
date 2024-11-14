package br.com.greenrank.config;

import java.sql.SQLException;

public class DatabaseConnectionFactory {
    private DatabaseConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static DatabaseConnection create() throws SQLException {
        return DatabaseConnectionImpl.getInstance();
    }
}
