package app.controller.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import app.service.mainService.BookService;


public class Home implements BaseController{
    @FXML
    private VBox mainPage;

    private ObservableList<String> bookSectionTitleList;

    private void getBookSectionList() {
        bookSectionTitleList = FXCollections.observableArrayList();
        bookSectionTitleList.addAll(
            "Top Rated",
            "History",
            "Science",
            "Fiction",
            "Non-Fiction",
            "Fantasy"
        );
    }

    @Override
    public void initialize() {
        getBookSectionList();
        for (String bookSectionTitle : bookSectionTitleList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/booksection.fxml"));
                VBox section = loader.load();
                mainPage.getChildren().add(section);

                BookSection controller = loader.getController();
                controller.InitBookSectionByTitle(bookSectionTitle);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
