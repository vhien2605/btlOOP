package app.controller.user.BookDetail;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookDetailController implements BaseController {
    @FXML
    private ImageView imageURL;

    @FXML
    private Button backToHomeButton;

    public void loadBook(Book book) {
        System.out.println(book.getImagePath());
        imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/" + book.getImagePath())));
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backToHomeButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");

        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

}
