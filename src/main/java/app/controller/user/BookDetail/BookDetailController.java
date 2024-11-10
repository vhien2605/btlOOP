package app.controller.user.BookDetail;

import app.controller.BaseController;
import app.domain.Book;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookDetailController implements BaseController{
    @FXML
    private ImageView imageURL;

    public void loadBook(Book book) {
        System.out.println(book.getImagePath());
        imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/" + book.getImagePath())));
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }
    
}
