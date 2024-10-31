package app.service.authService;

import java.io.*;
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
        String sessionId = Long.toString(System.currentTimeMillis()) + id;
        String sessionValue = id + " " + role;
        Properties localProperties = new Properties();
        localProperties.setProperty("sessionId", sessionId);
        try (BufferedWriter writerServer = new BufferedWriter(new FileWriter("./src/main/resources/serverSession.properties", true));
             FileOutputStream writerLocal = new FileOutputStream("./src/main/resources/localSession.properties");
        ) {
            writerServer.write(sessionId + "=" + sessionValue);
            writerServer.newLine();
            localProperties.store(writerLocal, null);
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
    public void clearWhenLogout() {
        String rootPath = System.getProperty("user.dir");
        String finalPath = rootPath + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "localSession.properties";
        File targetFile = new File(finalPath);
        if (targetFile.delete()) {
            System.out.println("deleted " + targetFile.getName());
        } else {
            System.out.println("delete " + targetFile.getName() + " failed");
        }
    }

    /**
     * verify Session method.
     *
     * @return String {@code "Session not found"} if can't map session
     * <br>
     * return String userId+role if mapping session successfully
     */
    public String verifySession() {
        Properties localProp = new Properties();
        Properties serverProp = new Properties();
        try (FileInputStream local = new FileInputStream("./src/main/resources/localSession.properties");
             FileInputStream server = new FileInputStream("./src/main/resources/serverSession.properties")
        ) {
            localProp.load(local);
            serverProp.load(server);
            String sessionId = localProp.getProperty("sessionId");
            return serverProp.getProperty(sessionId);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Session not found");
            ex.printStackTrace();
            return "Session not found";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Session is invalid");
            e.printStackTrace();
            return "Session is invalid";
        }
    }
}
