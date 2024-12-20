package br.com.greenrank.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnectionImpl implements DatabaseConnection{

    private static DatabaseConnectionImpl instance;
    private static Connection connection;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private DatabaseConnectionImpl() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUser(),
                    DatabaseConfig.getPassword()
            );
        } catch (ClassNotFoundException e) {
            logger.severe("Não foi localizada a classe Driver do Oracle: " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnectionImpl getInstance() throws SQLException {
        if (instance == null || connection.isClosed()) {
            instance = new DatabaseConnectionImpl();
        }
        return instance;
    }

    @Override
    public Connection get() throws SQLException {
        connection.setAutoCommit(false);
        return connection;
    }
}
