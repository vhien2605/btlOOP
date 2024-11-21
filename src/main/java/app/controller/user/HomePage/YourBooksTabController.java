package app.controller.user.HomePage;

import java.io.IOException;

import app.domain.Book;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class YourBooksTabController {
    private static final String allButton = "AllButton";
    private static final String pendingButton = "PendingButton";
    private static final String borrowingButton = "BorrowingButton";
    private static final String returnedButton = "ReturnedButton";

    private String currentButtonClicked;

    private MainHomePageController homeController;

    private ObservableList<Book> displayBookList;

    protected YourBooksTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        setDefault();
        handleAllButtonIsClicked();
    }

    public void handleAllButtonIsClicked() {
        currentButtonClicked = allButton;
        clearYourBooksMainPage();
        renderBookListByStatus(homeController.pendingBookList, Card.PENDING_STATUS);
        renderBookListByStatus(homeController.borrowingBookList, Card.BORROWING_STATUS);
        renderBookListByStatus(homeController.returnedBookList, Card.RETURNED_STATUS);
    }

    public void handlePendingButtonIsClicked() {
        currentButtonClicked = pendingButton;
        clearYourBooksMainPage();
        renderBookListByStatus(homeController.pendingBookList, Card.PENDING_STATUS);
    }

    public void handleBorrowingButtonIsClicked() {
        currentButtonClicked = borrowingButton;
        clearYourBooksMainPage();
        renderBookListByStatus(homeController.borrowingBookList, Card.BORROWING_STATUS);
    }

    public void handleReturnedButtonIsClicked() {
        currentButtonClicked = returnedButton;
        clearYourBooksMainPage();
        renderBookListByStatus(homeController.returnedBookList, Card.RETURNED_STATUS);
    }

    private void renderBookListByStatus(ObservableList<Book> bookList, String status) {
        for (Book book : bookList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/HomeTab/card.fxml"));
                Button card = loader.load();

                Card cardController = loader.getController();
                cardController.loadBookWithStatus(book, status);

                homeController.yourBooksMainPage.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearYourBooksMainPage() {
        ObservableList<javafx.scene.Node> childrens = homeController.yourBooksMainPage.getChildren();
        while (!childrens.isEmpty()) {
            childrens.remove(childrens.size() - 1);
        }
    }

    private void setDefault() {
        currentButtonClicked = allButton;
    }

}
