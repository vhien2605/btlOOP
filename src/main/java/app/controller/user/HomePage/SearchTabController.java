package app.controller.user.HomePage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookTab.MainBookController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.SessionException;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

        System.out.println(col + " LIKE %" + value + "%");

        bookList = bookService.search(col, value);
        renderBookListToTilePane();
    }

    private void renderBookListToTilePane() {
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
