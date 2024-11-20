package app.controller.admin.BookLoanTab;

import java.io.File;

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
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(pdfFilter);

            fileChooser.setInitialFileName("output.pdf");

            File file = fileChooser.showSaveDialog(FXMLResolver.getInstance().getStage());

            if (file != null) {
                String filePath = file.getAbsolutePath();
                if (!filePath.endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                PdfExportService.exportPaneToPdf(mainBookLoanCtrl.pane_data, filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Đã xảy ra lỗi khi xuất dữ liệu.");
        }
    }
}
