package app.controller.user.HomePage;

import java.io.IOException;

import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This class controls each BookSection
 */

public class BookDiscoverSection implements BaseController {
    /**
     * The limit number of books that can be displayed in a single section.
     */
    private static final int numberOfBooksInEachSection = 5;

    @FXML
    private Label bookDisplaySectionTitle;

    @FXML
    private HBox bookDisplaySection;

    private ObservableList<Book> bookList;

    private BookService bookService;

    public void InitBookSectionByTitle(String sectionName) {
        this.bookDisplaySectionTitle.setText(sectionName);
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
        return bookDisplaySectionTitle.getText();
    }

    public void getBookListLimitBooksToSection(ObservableList<Book> initBookList) {
        for (int i = 0; i < numberOfBooksInEachSection && i < initBookList.size(); i++) {
            bookList.add(initBookList.get(i));
        }
    }

    @Override
    public void initialize() {

    }
    
}
