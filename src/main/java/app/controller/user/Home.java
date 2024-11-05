package app.controller.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import app.controller.BaseController;
import app.domain.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Home implements BaseController{
    @FXML
    public HBox bookSection;

    List <Book> getBookList() {
        return List.of(new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"),

         new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"),
         
         new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"),
         
         new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"),
         
         new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"),
         
         new Book("Harry Potter", "Harry Potter", 
        "J.K. Rowling","Harry Potter","Harry Potter",
        "Harry Potter", 5, 5,
         "src/main/resources/image/book/what_the_dead_know.jpg"));
    }

    @Override
    public void initialize() {
        List <Book> books = getBookList();
        loadBooks(books);
    }

    private void loadBooks(List<Book> books) {
        for (Book book : books) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/card.fxml"));
                VBox card = loader.load();

                Card controller = loader.getController();
                controller.setImage(book);

                // Card controller = loader.getController();
                // controller.setBookData(book);

                // booksContainer.getChildren().add(bookBlock);
                bookSection.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
