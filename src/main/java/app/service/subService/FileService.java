package app.service.subService;

import app.domain.BorrowReport;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * FileService handle interacting with file
 */
public class FileService {
    /**
     * Method receive {@code File} from Front-end and save to server.
     * The root path is image .
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
     * create QR image method service.
     *
     * @param report       report Object
     * @param targetFolder target folder
     * @return JSON String the report information
     */
    public String createQRImage(BorrowReport report, String targetFolder) {
        String rootPath = System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "main" + File.separator + "resources"
                + File.separator + "image";
        String fileName = System.currentTimeMillis() + "-" + "qr.jpg";
        String finalPath = rootPath + File.separator + targetFolder + File.separator + fileName;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", report.getId());
        jsonObject.put("userId", report.getUserId());
        jsonObject.put("bookId", report.getBookId());
        jsonObject.put("borrowDate", report.getBorrowDate());
        jsonObject.put("returnDate", report.getReturnDate());
        jsonObject.put("expectedReturnDate", report.getExpectedReturnDate());
        jsonObject.put("status", report.getStatus());
        jsonObject.put("qrcodeUrl", report.getQrcodeUrl());
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(jsonObject.toString(), BarcodeFormat.QR_CODE, 250, 250);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(finalPath));
            return fileName;
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * scanCode method.
     *
     * @param qrImageFile qrImageFile
     * @return {@link BorrowReport} object parse from qr code
     */
    public BorrowReport scanQRCode(File qrImageFile) {
        try {
            byte[] imageBytes = Files.readAllBytes(qrImageFile.toPath());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);
            JSONObject jsonObject = new JSONObject(result.getText());
            return new BorrowReport(jsonObject.getInt("id"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("bookId"),
                    jsonObject.getString("borrowDate"),
                    jsonObject.has("returnDate") && !jsonObject.isNull("returnDate")
                            ? jsonObject.getString("returnDate")
                            : null,
                    jsonObject.getString("expectedReturnDate"),
                    jsonObject.getString("status"),
                    jsonObject.has("returnDate") && !jsonObject.isNull("returnDate")
                            ? jsonObject.getString("returnDate")
                            : null);

        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
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
