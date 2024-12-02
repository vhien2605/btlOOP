package app.service.subService.exportFileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.WritableImage;

/**
 * One of behaviors.
 */
public class PdfExportStrategy implements ExportStrategy {

    /**
     * PDF exporting method.
     *
     * @param pane Container in javafx.
     * @return {@code File} PDF
     */
    @Override
    public File export(Node pane) {
        // Take a photo from pane
        WritableImage snapshot = pane.snapshot(null, null);

        try {
            // Save temporary image to PNG file
            File tempImageFile = File.createTempFile("pane_snapshot", ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", tempImageFile);

            // Create temporary PDF file
            File tempPdfFile = File.createTempFile("exported_pane", ".pdf");

            // Create PDF writer
            PdfWriter writer = new PdfWriter(new FileOutputStream(tempPdfFile));
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add images to PDF
            com.itextpdf.io.image.ImageData imageData = com.itextpdf.io.image.ImageDataFactory
                    .create(tempImageFile.getAbsolutePath());
            com.itextpdf.layout.element.Image pdfImage = new com.itextpdf.layout.element.Image(imageData);

            // Add images to PDF documents
            document.add(pdfImage);

            document.close();

            // Delete temporary image file
            tempImageFile.delete();

            System.out.println("PDF exported successfully to temporary file: " + tempPdfFile.getAbsolutePath());

            return tempPdfFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
