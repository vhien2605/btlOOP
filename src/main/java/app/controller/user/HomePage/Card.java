package app.controller.user.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

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
        // imageURL.setPreserveRatio(false);
        try {
            String rootPath = Paths.get("").toAbsolutePath().toString();
            String imagePath = Paths.get(rootPath, "src", "main", "resources", "image", "book", book.getImagePath())
                    .toAbsolutePath().toString();
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("Image file not found: " + imagePath);
                return;
            }
            String imageURI = imageFile.toURI().toString();
            Image image = new Image(imageURI);
            imageURL.setImage(image);
        } catch (Exception e) {
            System.out.println("Load image fail");
            e.printStackTrace();
            System.out.println(e.getMessage());
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
