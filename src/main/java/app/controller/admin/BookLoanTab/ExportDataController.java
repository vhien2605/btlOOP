package app.controller.admin.BookLoanTab;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import app.config.ViewConfig.FXMLResolver;
import app.service.subService.PdfExportService;
import javafx.stage.FileChooser;

public class ExportDataController {
    final MainBookLoanController mainBookLoanCtrl;

    public ExportDataController(MainBookLoanController mainBookLoanCtrl) {
        this.mainBookLoanCtrl = mainBookLoanCtrl;
    }

    void init() {
        mainBookLoanCtrl.exportButton.setOnAction(e -> exportData());
    }

    void exportData() {
        try {
            File tempPdfFile = PdfExportService.exportPaneToPdf(mainBookLoanCtrl.pane_data);
            if (tempPdfFile == null) {
                System.err.println("Không thể tạo file PDF tạm.");
                return;
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(pdfFilter);
            fileChooser.setInitialFileName("output.pdf");

            File selectedFile = fileChooser.showSaveDialog(FXMLResolver.getInstance().getStage());
            if (selectedFile != null) {
                String targetPath = selectedFile.getAbsolutePath();
                if (!targetPath.endsWith(".pdf")) {
                    targetPath += ".pdf";
                }

                File targetFile = new File(targetPath);
                try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
                    Files.copy(tempPdfFile.toPath(), outputStream);
                    System.out.println("Xuất PDF thành công: " + targetPath);
                }

                tempPdfFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi xuất dữ liệu");
        }
    }
}
