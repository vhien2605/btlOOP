package app.controller.helper;

import java.io.File;

import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.controller.user.BookLoan.BookLoanController;
import app.service.subService.GMailer;
import app.service.subService.exportFileService.ExportContext;
import app.service.subService.exportFileService.PdfExportStrategy;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class SendMailHelper {
    /**
     * Admin
     * 
     * Function to send email when user successfully borrows book.
     *
     * @param bookLoanController BookLoanTab controller renders book loan card
     *                           details.
     * @param email              Recipient email address.
     */
    public static void sendMail(MainBookLoanController bookLoanController, String email) {
        sendMailWithpane(bookLoanController.getPaneData(), email);
    }

    /**
     * User
     * 
     * Function to send email when user successfully borrows book.
     *
     * @param bookLoanController BookLoanTab controller renders book loan card
     *                           details.
     * @param email              Recipient email address.
     */
    public static void sendMailForUser(BookLoanController bookLoanController, String email) {
        sendMailWithpane(bookLoanController.getPaneData(), email);
    }

    private static void sendMailWithpane(Pane pane, String email) {
        Platform.runLater(() -> {
            File tempPdfFile = new ExportContext(new PdfExportStrategy()).export(pane);

            if (tempPdfFile != null) {
                new Thread(() -> {
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
}
