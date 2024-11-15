package app.controller.user.HomePage;

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
        imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/" + book.getImagePath())));
    }

    public void handleCardEvent(ActionEvent e) {
        if (e.getSource() == cardButton) {
            loadBookDetail(book);
        }
    }

    private void loadBookDetail(Book book) {
        FXMLResolver.getInstance().renderScene("bookdetail/bookdetail");
        BookDetailController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.loadBook(book);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    
}
