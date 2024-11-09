package app.controller.user;

import app.controller.BaseController;
import app.domain.Book;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;



public class Card implements BaseController{
    @FXML  
    private ImageView imageURL;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    public void loadBook(Book book) {
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/" + book.getImagePath())));
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    
}
