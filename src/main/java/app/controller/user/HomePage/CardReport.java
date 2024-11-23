package app.controller.user.HomePage;

import java.io.IOException;
import java.io.InputStream;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.user.BookDetail.BookDetailController;
import app.controller.user.BookLoan.BookLoanController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class CardReport implements BaseController{
    public static final String PENDING_STATUS = "pending";
    public static final String BORROWING_STATUS = "borrowing";
    public static final String RETURNED_STATUS = "returned";

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

    private Book book;

    private String status;

    public void loadBookWithStatus(Book book, String status) {
        this.book = book;
        this.status = status;
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
        if (status == PENDING_STATUS) {
            pendingLabel.setVisible(true);
        } else if (status == BORROWING_STATUS) {
            borrowingLabel.setVisible(true);
        } else if (status == RETURNED_STATUS) {
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
        controller.LoadBookLoanWithBookAndStatus(book, status);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    
}
