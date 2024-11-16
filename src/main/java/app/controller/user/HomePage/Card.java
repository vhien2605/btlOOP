package app.controller.user.HomePage;

import java.io.InputStream;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.user.BookDetail.BookDetailController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class Card implements BaseController{
    @FXML
    private Button cardButton;

    @FXML  
    private ImageView imageURL;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    private Book book;

    public void loadBook(Book book) {
        this.book = book;
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
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

    public void handleCardEvent(ActionEvent e) {
        if (e.getSource() == cardButton) {
            loadBookDetail(book);
        }
    }

    private void loadBookDetail(Book book) {
        FXMLResolver.getInstance().renderScene("user/bookdetail/bookdetail");
        BookDetailController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.loadBook(book);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    
}
