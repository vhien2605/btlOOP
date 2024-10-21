package app.service.subService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * FileService handle interacting with file
 */
public class FileService {
    /**
     * Method receive {@code File} from Front-end and save to server
     *
     * @param imageFile    imageFile from client
     * @param targetFolder targetFolder
     * @return String fileName
     */
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
        try {
            Files.copy(imageFile.toPath(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            System.out.println("save file " + fileName + " failed");
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * method handle delete file in target folder
     *
     * @param fileName     String fileName
     * @param targetFolder targetFolder have deleted file
     */
    public void handleDeleteImage(String fileName, String targetFolder) {
        String rootPath = System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "main" + File.separator + "resources"
                + File.separator + "image";
        String finalFilePath = rootPath + File.separator + targetFolder + File.separator + fileName;
        File currentFile = new File(finalFilePath);
        if (currentFile.delete()) {
            System.out.println("deleted file " + fileName);
        } else {
            System.out.println("delete file failed");
        }
    }
}
