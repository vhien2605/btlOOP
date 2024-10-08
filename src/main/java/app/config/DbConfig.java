package app.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConfig class to access to database for excute query
 * use Singleton design pattern to only use one database connection instance in application
 */
public class DbConfig {
    private Connection connection;
    private static DbConfig dbConfig;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javafx";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "hienhien123@";

    private DbConfig() {

    }

    /**
     * Create Connection function
     * @return JavaFx Connection instance for connecting to database
     */
    public Connection getConnection() {
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

    /**
     * getInstance function for singleton design pattern
     *
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
}
