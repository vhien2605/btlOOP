package app.controller.user.HomePage;

import java.io.IOException;

import app.config.ViewConfig.FXMLResolver;
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
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class YourBooksTabController {
    private MainHomePageController homeController;

    public SurfaceUserDTO user;

    public ObservableList<BorrowReport> allStatusBorrowReportList;
    
    public ObservableList<BorrowReport> pendingBorrowReportList;

    public ObservableList<BorrowReport> borrowingBorrowReportList;

    public ObservableList<BorrowReport> returnedBorrowReportList;

    private ReportService reportService;

    private BookService bookService;

    public AuthenticationService authService;

    protected YourBooksTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        reportService = new ReportService(new ReportRepository(), new UserService(new UserRepository()), new BookService(new BookRepository()));
        bookService = new BookService(new BookRepository());
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        getUserInfo();
        getAllReportToThisCurrentUser();
        TransferBorrowReportToBookLists();
        setDefault();
    }

    private void TransferBorrowReportToBookLists() {
        pendingBorrowReportList = FXCollections.observableArrayList();
        borrowingBorrowReportList = FXCollections.observableArrayList();
        returnedBorrowReportList = FXCollections.observableArrayList();
        for (BorrowReport borrowReport : allStatusBorrowReportList) {
            if (borrowReport.getStatus().equals(BorrowReport.PENDING)) {
                pendingBorrowReportList.add(borrowReport);
            } else if (borrowReport.getStatus().equals(BorrowReport.BORROWED)) {
                borrowingBorrowReportList.add(borrowReport);
            } else if (borrowReport.getStatus().equals(BorrowReport.RETURNED)) {
                returnedBorrowReportList.add(borrowReport);
            }
        }
    }

    private void getAllReportToThisCurrentUser() {
        allStatusBorrowReportList = FXCollections.observableArrayList();
        allStatusBorrowReportList = reportService.findByOneColumn("userID", user.getId());
    }

    public void handleAllButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(pendingBorrowReportList, CardReport.PENDING_STATUS);
        renderBookListByStatus(borrowingBorrowReportList, CardReport.BORROWING_STATUS);
        renderBookListByStatus(returnedBorrowReportList, CardReport.RETURNED_STATUS);
    }

    public void handlePendingButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(pendingBorrowReportList, CardReport.PENDING_STATUS);
    }

    public void handleBorrowingButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(borrowingBorrowReportList, CardReport.BORROWING_STATUS);
    }

    public void handleReturnedButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(returnedBorrowReportList, CardReport.RETURNED_STATUS);
    }

    private void renderBookListByStatus(ObservableList<BorrowReport> borrowReportList, String status) {
        for (BorrowReport borrowReport : borrowReportList) {
            Book book = bookService.findByISBN(borrowReport.getBookId());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/HomeTab/cardreport.fxml"));
                Button cardReport = loader.load();

                CardReport cardController = loader.getController();
                cardController.loadBookWithStatus(book, status);

                homeController.yourBooksMainPage.getChildren().add(cardReport);
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
        handleAllButtonIsClicked();
    }

    private void getUserInfo() {
        try {
            user = authService.getCurrentUser();
        } catch (SessionException exception) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }
}
