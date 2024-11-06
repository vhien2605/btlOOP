package app.controller.admin.AllIssueBookTab;

import java.util.List;

import app.controller.BaseController;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainAllIssueController implements BaseController {
    @FXML
    TextField searchTextFiled;

    @FXML
    Button allButton, pendingButton, borrowedButton, returnedButton;

    List<Button> buttons;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentVBox;

    ReportService reportService;

    UserService userService;

    BookService bookService;

    ObservableList<BorrowReport> listBorrowReport;

    String currStatus;

    @Override
    public void initialize() {
        userService = new UserService(new UserRepository());
        bookService = new BookService(new BookRepository());
        reportService = new ReportService(new ReportRepository(), userService, bookService);

        new AllSetUp().initMainAllIssuedCtrl(this);

        buttons = List.of(allButton, pendingButton, borrowedButton, returnedButton);

        getAll();
    }

    private void loadData() {
        contentVBox.getChildren().clear();

        for (BorrowReport data : listBorrowReport) {
            try {
                String path = "/view/admin/allIssueBookTab/issued_row.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Pane pane = loader.load();

                MainIssuedRowController rowController = loader.getController();
                rowController.setMainAllIssueController(this);
                rowController.loadData(data);

                contentVBox.getChildren().add(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    void updateButtonStyle(int ordinalNumber) {

        for (int i = 0; i < buttons.size(); i++) {
            if (i != ordinalNumber) {
                buttons.get(i).setStyle(
                        "-fx-background-color: white; -fx-text-fill: #0b9710; -fx-border-color: #0b9710;  -fx-border-radius: 3px;");
            } else {
                buttons.get(i).setStyle(
                        "-fx-background-color: #0b9710; -fx-text-fill: white; -fx-border-color: #0b9710;  -fx-border-radius: 3px;");
            }
        }
    }

    void getAll() {
        currStatus = "All";
        updateButtonStyle(0);
        listBorrowReport = reportService.getAllReports();
        loadData();
    }

    void getPending() {
        currStatus = BorrowReport.PENDING;
        updateButtonStyle(1);
        listBorrowReport = reportService.findByStatus(BorrowReport.PENDING);
        loadData();
    }

    void getBorrowed() {
        currStatus = BorrowReport.BORROWED;
        updateButtonStyle(2);
        listBorrowReport = reportService.findByStatus(BorrowReport.BORROWED);
        loadData();
    }

    void getReturned() {
        currStatus = BorrowReport.RETURNED;
        updateButtonStyle(3);
        listBorrowReport = reportService.findByStatus(BorrowReport.RETURNED);
        loadData();
    }

    void resetData() {
        if (currStatus == "All") {
            getAll();
        } else if (currStatus == BorrowReport.PENDING) {
            getPending();
        } else if (currStatus == BorrowReport.BORROWED) {
            getBorrowed();
        } else {
            getReturned();
        }
    }

}
