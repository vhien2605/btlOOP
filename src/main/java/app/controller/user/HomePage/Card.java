package app.controller.user.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.user.BookDetail.BookDetailController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Card implements BaseController {
    @FXML
    private Button cardButton;

    @FXML
    private ImageView imageURL;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    @FXML
    private VBox cardVBox;

    private Book book;

    public void loadBook(Book book) {
        this.book = book;
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        loadImage(book);
    }

    private void loadImage(Book book) {
        String path = "/image/book/" + book.getImagePath();
        InputStream inputStream = getClass().getResourceAsStream(path);
        if (inputStream == null) {
            imageURL.setPreserveRatio(false);
            File file = new File(path);
            if (file.exists()) {
                try {
                    inputStream = new FileInputStream(file);
                    imageURL.setImage(new Image(inputStream));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return;
        } else {
            imageURL.setPreserveRatio(false);
            imageURL.setImage(new Image(inputStream));
        }
    }

    public void handleCardEvent(ActionEvent e) {
        if (e.getSource() == cardButton) {
            loadBookDetail(book);
        }
    }

    private void loadBookDetail(Book book) {
        FXMLResolver.getInstance().renderScene("user/bookdetail/bookdetail");
        BookDetailController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.loadBookWithStatus(book);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }


}
