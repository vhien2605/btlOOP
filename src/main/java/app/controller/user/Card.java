package app.controller.user;

import app.controller.BaseController;
import app.domain.Book;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card implements BaseController{
    @FXML  
    private ImageView imageURL;

    public void setImage(Book book) {
        if (book == null) {
            System.out.println("dfgsfhdfghd1242345");
            return;
        }
        // System.out.println("Image path :" + book.getImagePath());
        // imageURL.setImage(new Image(book.getImagePath()));
        imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/what_the_dead_know.jpg")));
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    
}
