package app.service.authService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SessionService {
    public void createSession(String id, String role) {
        Properties properties = new Properties();
        properties.setProperty("ID", id);
        properties.setProperty("ROLE", role);
        AuthUser.getInstance().createCurrentUser(id);
        try (FileOutputStream writter = new FileOutputStream("./src/main/resources/session.properties")) {
            properties.store(writter, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

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
