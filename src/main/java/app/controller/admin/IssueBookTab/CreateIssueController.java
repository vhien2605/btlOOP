package app.controller.admin.IssueBookTab;

import java.io.File;

import com.google.zxing.qrcode.encoder.QRCode;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.domain.BorrowReport;
import app.service.subService.GMailer;
import app.service.subService.PdfExportService;
import javafx.application.Platform;

public class CreateIssueController {
    final MainIssueController mainIssueCtrl;

    public CreateIssueController(MainIssueController mainIssueCtrl) {
        this.mainIssueCtrl = mainIssueCtrl;
    }

    void init() {
        mainIssueCtrl.issueBookButton.setOnAction(e -> createIssue());
    }

    private void createIssue() {
        BorrowReport data = mainIssueCtrl.getData();

        if (data == null) {
            return;
        }

        if (mainIssueCtrl.bookInfo.getBookRemaining() <= 0) {
            mainIssueCtrl.showAlert.showAlert("The number of books left is not enough!", "error");
            return;
        } else {
            mainIssueCtrl.bookInfo.setBookRemaining(mainIssueCtrl.bookInfo.getBookRemaining() - 1);
            if (!mainIssueCtrl.bookService.handleUpdateOne(mainIssueCtrl.bookInfo)) {
                mainIssueCtrl.showAlert.showAlert("Create fail new borrow report!", "error");
                return;
            }
        }

        if (mainIssueCtrl.reportService.handleSave(data)) {
            createQRImage(data);

            String currentPath = "admin/issueBookTab/issuebook_tab";
            FXMLResolver resolver = FXMLResolver.getInstance();
            resolver.renderScene("admin/bookLoanTab/bookloan_tab");

            mainIssueCtrl.showAlert.showAlert("Create success new borrow report!", "success");

            MainBookLoanController bookLoanController = resolver.getLoader().getController();
            bookLoanController.renderData(data, currentPath);

            sendMail(bookLoanController);
        } else {
            mainIssueCtrl.showAlert.showAlert("Create fail new borrow report !", "error");
        }

    }

    void sendMail(MainBookLoanController bookLoanController) {
        Platform.runLater(() -> {
            File tempPdfFile = PdfExportService.exportPaneToPdf(bookLoanController.getPaneData());

            if (tempPdfFile != null) {
                new Thread(() -> {
                    String email = mainIssueCtrl.userInfo.getEmail();

                    try {
                        new GMailer(email).sendMail(
                                "Bill mượn sách",
                                "Xin chào quý khách hàng\n\n"
                                        + "Cảm ơn bạn đã sử dụng dịch vụ thư viện online của 3HTeam chúng tôi\n\n"
                                        + "Khi đến trả sách, vui lòng mang bill đến để scan thông tin\n\n"
                                        + "Trân trọng cảm ơn!",
                                tempPdfFile);

                        System.out.println("Gửi email thành công!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Gửi email thất bại!");
                    }
                }).start();
            } else {
                System.err.println("Lỗi khi tạo file PDF.");
            }
        });
    }

    void createQRImage(BorrowReport data) {
        String path = mainIssueCtrl.fileService.createQRImage(data, "QRcode");
        if (path != null) {
            data.setQrcodeUrl(path);
            mainIssueCtrl.reportService.handleUpdateOne(data);
        } else {
            System.out.println("file service return path null");
        }
    }
}
