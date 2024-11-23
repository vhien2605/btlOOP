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

    public AuthenticationService authService;

    protected YourBooksTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        reportService = new ReportService(new ReportRepository(), new UserService(new UserRepository()), new BookService(new BookRepository()));
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
        renderBookListByStatus(pendingBorrowReportList);
        renderBookListByStatus(borrowingBorrowReportList);
        renderBookListByStatus(returnedBorrowReportList);
    }

    public void handlePendingButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(pendingBorrowReportList);
    }

    public void handleBorrowingButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(borrowingBorrowReportList);
    }

    public void handleReturnedButtonIsClicked() {
        clearYourBooksMainPage();
        renderBookListByStatus(returnedBorrowReportList);
    }

    private void renderBookListByStatus(ObservableList<BorrowReport> borrowReportList) {
        for (BorrowReport borrowReport : borrowReportList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/HomeTab/cardreport.fxml"));
                Button cardReport = loader.load();

                CardReport cardReportController = loader.getController();
                cardReportController.loadCardReport(borrowReport);

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
