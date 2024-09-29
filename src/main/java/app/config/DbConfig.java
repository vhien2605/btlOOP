package app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    private Connection connection;
    private static DbConfig dbConfig;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javafx";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "hienhien123@";

    private DbConfig() {

    }

    private Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                System.out.println("Connect database successfully");
                return connection;
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("connect database failed");
            return null;
        }
    }

    public static DbConfig getInstance() {
        if (dbConfig == null) {
            dbConfig = new DbConfig();
            return dbConfig;
        }
        return dbConfig;
    }
}
