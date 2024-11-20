package app.controller.user.BookDetail;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.MainHomePageController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.SurfaceUserDTO;
import app.domain.User;
import app.exception.auth.SessionException;
import app.repository.ReportRepository;
import app.service.mainService.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookDetailController implements BaseController {
    @FXML
    private ImageView imageURL;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Button sendBorrowBookRequestButton;

    @FXML
    private DatePicker borrowDatePicker;

    @FXML
    private DatePicker expectedReturnDatePicker;

    @FXML
    private Label bookNameLabel;

    private ShowAlert showAlert;

    private ReportService reportService;

    private Book book;

    public void loadBook(Book book) {
        this.book = book;
        loadImage(book);
    }

    private void loadImage(Book book) {
        InputStream inputStream = getClass().getResourceAsStream("/image/book/" + book.getImagePath());
        if (inputStream == null) {
            imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/book-default-cover.jpg")));
        } else {
            imageURL.setImage(new Image(inputStream));

        }
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backToHomeButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        } else if (e.getSource() == sendBorrowBookRequestButton) {
            sendBorrowBookRequest();
        }
    }

    private void sendBorrowBookRequest() {
        if (!validateFields()) {
            return;
        }
        BorrowReport borrowReport = new BorrowReport(
                0,
                MainHomePageController.user.getId(),
                book.getId(),
                borrowDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                null,
                expectedReturnDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                BorrowReport.PENDING,
                ""
        );
        if (reportService.handleSave(borrowReport)) {
            showAlert.showAlert("Create new borrow book request successfully!", "success");
        } else {
            showAlert.showAlert("Create new borrow book request fail!", "error");

        }
    }

    private boolean validateFields() {
        if (borrowDatePicker.getValue() == null) {
            showAlert.showAlert("Borrow Date must be selected!", "error");
            return false;
        }

        if (expectedReturnDatePicker.getValue() == null) {
            showAlert.showAlert("Expected Return Date must be selected!", "error");
            return false;
        }

        /**
         * // TO DO : Check borrowDate < expectedReturnDate
         */

        return true;
    }

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        reportService = new ReportService(new ReportRepository(), null, null);
    }

    
}
