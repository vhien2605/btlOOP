package app.service;

import java.io.*;

public class UploadFileService {
    public String handleSaveImage(File imageFile, String targetFolder) {
        if (imageFile == null) {
            return "";
        }
        String rootPath = System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "main" + File.separator + "resources"
                + File.separator + "image";
        String fileName = System.currentTimeMillis() + "-" + imageFile.getName();
        String finalPath = rootPath + File.separator + targetFolder + File.separator + fileName;
        File serverFile = new File(finalPath);
        try (FileInputStream fis = new FileInputStream(imageFile);
             FileOutputStream fos = new FileOutputStream(serverFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            return fileName;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
