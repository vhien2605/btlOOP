package app.controller.user;

import java.io.IOException;

import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class BookSection implements BaseController {
    private static final int numberOfBooksInEachSection = 6;

    @FXML
    private Label bookSectionTitle;

    @FXML
    private HBox bookDisplaySection;

    private ObservableList<Book> bookList;

    private BookService bookService;

    public void InitBookSectionByTitle(String sectionName) {
        this.bookSectionTitle.setText(sectionName);

        bookService = new BookService(new BookRepository());
        this.bookList = FXCollections.observableArrayList();
        ObservableList<Book> temporaryBookList = bookService.findByCategory(sectionName);
        for (int i = 0; i < numberOfBooksInEachSection && i < temporaryBookList.size(); i++) {
            this.bookList.add(temporaryBookList.get(i));
        }
        temporaryBookList = null;
        addCardToFXML();
    }

    private void addCardToFXML() {
        for (Book book : bookList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/card.fxml"));
                Button card = loader.load();
                bookDisplaySection.getChildren().add(card);

                Card controller = loader.getController();
                controller.loadBook(book);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
    }

    public ObservableList<Book> getBookList() {
        return bookList;
    }

    public String getBookSectionTitle() {
        return bookSectionTitle.getText();
    }

    public void getBookListLimitBooksToSection(ObservableList<Book> initBookList) {
        for (int i = 0; i < numberOfBooksInEachSection && i < initBookList.size(); i++) {
            bookList.add(initBookList.get(i));
        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }
    
}
