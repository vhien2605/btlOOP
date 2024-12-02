package app.controller.user.HomePage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class SearchTabController {
    private MainHomePageController homeController;

    private static final String ISBN_VALUE = "ISBN";
    private static final String TITLE_VALUE = "Title";
    private static final String AUTHOR_VALUE = "Author";
    private static final String CATEGORY_VALUE = "Category";

    private static final Map<String, String> DISPLAY_TO_VALUE_MAP = new LinkedHashMap<>();

    static {
        DISPLAY_TO_VALUE_MAP.put(ISBN_VALUE, "id");
        DISPLAY_TO_VALUE_MAP.put(TITLE_VALUE, "name");
        DISPLAY_TO_VALUE_MAP.put(AUTHOR_VALUE, "author");
        DISPLAY_TO_VALUE_MAP.put(CATEGORY_VALUE, "category");
    }

    private BookService bookService;

    private ObservableList<Book> bookList;

    protected SearchTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        ObservableList<String> displayValues = FXCollections.observableArrayList(DISPLAY_TO_VALUE_MAP.keySet());
        homeController.choiceBoxSearchFilter.setItems(displayValues);
        homeController.choiceBoxSearchFilter.setValue(ISBN_VALUE);

        bookService = new BookService(new BookRepository());

        setHandleEvent();
        setDefault();
    }

    private void setHandleEvent() {
        homeController.searchBoxTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchBook();
            }
        });

        homeController.choiceBoxSearchFilter.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchBook();
                });
    }

    private void setDefault() {
        searchBook();
    }

    private void searchBook() {
        String col = DISPLAY_TO_VALUE_MAP.get(homeController.choiceBoxSearchFilter.getValue());
        String value = homeController.searchBoxTextField.getText();

        bookList = bookService.search(col, value);
        renderBookListToTilePane();
    }

    private void renderBookListToTilePane() {
        homeController.searchBooksMainPage.getChildren().clear();
        for (Book book : bookList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/card.fxml"));
                Button card = loader.load();
                homeController.searchBooksMainPage.getChildren().add(card);
                Card controller = loader.getController();
                controller.loadBook(book);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
