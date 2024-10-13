package app.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBConfig class to access to database for excute query
 * use Singleton design pattern to only use one database connection instance in application
 */
public class DbConfig {
    private Connection connection;
    private static DbConfig dbConfig;
    private static String DB_URL;
    private static String USER_NAME;
    private static String PASSWORD;

    private DbConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }
          
            prop.load(input);

            DB_URL = prop.getProperty("DB_URL");
            USER_NAME = prop.getProperty("USER_NAME");
            PASSWORD = prop.getProperty("PASSWORD");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * JDBC Connection getter
     *
     * @return JavaFx Connection instance for connecting to database
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * getInstance function for singleton design pattern
     *
     * @return current DBConfig instance or new instance
     */
    public static DbConfig getInstance() {
        if (dbConfig == null) {
            dbConfig = new DbConfig();
            return dbConfig;
        }
        return dbConfig;
    }

    /**
     * Create JDBC Connection instance
     */
    public void initializeConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                System.out.println("Connect database successfully");
            }
        } catch (SQLException e) {
            System.out.println("connect database failed");
            System.out.println(e.getMessage());
            connection = null;
        }
    }
}
