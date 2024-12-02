package app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * {@link DbConfig} class to access to database for excute query
 * use Singleton design pattern to only use one database connection instance in application
 * <p>
 * Example for singleton
 * </p>
 * <pre>{@code
 * private static DbConfig dbConfig;
 *
 * private DbConfig() {
 *     // Private constructor to prevent form create new instance
 * }
 * public static DbConfig getInstance() {
 *     if (dbConfig == null) {
 *         dbConfig = new DbConfig();
 *     }
 *     return dbConfig;
 * }
 * }</pre>
 */
public class DbConfig {
    private static DbConfig dbConfig;
    private HikariConfig config;
    private HikariDataSource ds;

    /**
     * Create {@link HikariConfig} and {@link HikariDataSource}
     * <p>
     * {@link HikariConfig} : stores the properties to get connection pool to database
     * <br>
     * {@link HikariDataSource} : represent for the database in Java, help us to get Connection, reuse
     * old Connection by the Object Pool design pattern
     * </p>
     */
    private DbConfig() {
        String fileName = System.getProperty("db.config", "database.properties");
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }
            prop.load(input);
            config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("DB_URL"));
            config.setUsername(prop.getProperty("USER_NAME"));
            config.setPassword(prop.getProperty("PASSWORD"));
            ds = new HikariDataSource(config);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Get {@link  Connection} from Hikari Connection pool
     *
     * @return instance of {@link Connection} in pool
     * @throws SQLException when can't get Connection from pool
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * getInstance function for singleton design pattern
     *
     * @return current {@link DbConfig} instance or new instance
     */
    public static DbConfig getInstance() {
        if (dbConfig == null) {
            dbConfig = new DbConfig();
            return dbConfig;
        }
        return dbConfig;
    }

    /**
     * Close connection pool
     */
    public void close() {
        ds.close();
    }
}
