package app.service.authService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import app.domain.User;

/**
 * {@link SessionService} for handling authorization after authentication
 * Create session to get Role for {@link User}
 *
 * @author hienonichan
 */
public class SessionService {
    /**
     * Create session method.
     * <p>
     * After checking username and password, server will create
     * an user session to keep the interacting
     * </p>
     *
     * @param id   {@link User}'s id
     * @param role Attribute role for authorization
     */
    public void createSession(String id, String role) {
        Properties properties = new Properties();
        properties.setProperty("ID", id);
        properties.setProperty("ROLE", role);
        try (FileOutputStream writter = new FileOutputStream("./src/main/resources/session.properties")) {
            properties.store(writter, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Clear session method.
     * <p>
     * Clear session when user logout or the application shutdown
     * </p>
     */
    public void clear() {
        String rootPath = System.getProperty("user.dir");
        String finalPath = rootPath + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "session.properties";
        File targetFile = new File(finalPath);
        if (targetFile.delete()) {
            System.out.println("deleted " + targetFile.getName());
        } else {
            System.out.println("delete " + targetFile.getName() + " failed");
        }
    }
}
