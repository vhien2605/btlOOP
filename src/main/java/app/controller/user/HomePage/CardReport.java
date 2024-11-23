package app.controller.user.HomePage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.user.BookDetail.BookDetailController;
import app.controller.user.BookLoan.BookLoanController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;



public class CardReport implements BaseController{
    @FXML
    private Button cardButton;

    @FXML  
    private ImageView imageURL;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    @FXML
    private Label pendingLabel;

    @FXML
    private Label borrowingLabel;

    @FXML
    private Label returnedLabel;

    private BorrowReport borrowReport;

    private Book book;

    private String status;

    private BookService bookService;

    public void loadCardReport(BorrowReport borrowReport) {
        this.borrowReport = borrowReport;
        this.book = bookService.findByISBN(borrowReport.getBookId());
        this.status = borrowReport.getStatus();
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        loadImage(book);
        setStatus();
    }

    private void loadImage(Book book) {
        InputStream inputStream = getClass().getResourceAsStream("/image/book/" + book.getImagePath());
        if (inputStream == null) {
            imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/book-default-cover.jpg")));
        } else {
            imageURL.setImage(new Image(inputStream));

        }
    }

    private void setStatus() {
        pendingLabel.setVisible(false);
        borrowingLabel.setVisible(false);
        returnedLabel.setVisible(false);
        if (status.equals(BorrowReport.PENDING)) {
            pendingLabel.setVisible(true);
        } else if (status.equals(BorrowReport.BORROWED)) {
            borrowingLabel.setVisible(true);
        } else if (status.equals(BorrowReport.RETURNED)) {
            returnedLabel.setVisible(true);
        }
    }

    public void handleCardEvent(ActionEvent e) {
        if (e.getSource() == cardButton) {
            loadBookLoan();
        }
    }

    private void loadBookLoan() {
        FXMLResolver.getInstance().renderScene("user/bookloan/bookloan");
        BookLoanController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.LoadBookLoan(book, borrowReport, status);    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
    }

    
}
