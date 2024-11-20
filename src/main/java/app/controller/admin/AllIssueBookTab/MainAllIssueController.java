package app.controller.admin.AllIssueBookTab;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.controller.BaseController;
import app.controller.admin.Panel.SidebarController;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import app.service.subService.FileService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainAllIssueController implements BaseController {
    @FXML
    TextField searchBoxTextField;

    @FXML
    Button allButton, pendingButton, borrowedButton, returnedButton, scanQRButton;

    List<Button> buttons;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentVBox;

    @FXML
    ChoiceBox<String> choiceBoxSearchFilter;

    ReportService reportService;

    UserService userService;

    BookService bookService;

    FileService fileService;

    ObservableList<BorrowReport> listBorrowReport;

    static final String USER_ID_VALUE = "User id";
    static final String ID_VALUE = "Issue id";

    static final Map<String, String> DISPLAY_TO_VALUE_MAP = new LinkedHashMap<>();
    static {
        DISPLAY_TO_VALUE_MAP.put(USER_ID_VALUE, "userId");
        DISPLAY_TO_VALUE_MAP.put(ID_VALUE, "id");
    }

    String currStatus = "";

    String colSearch = DISPLAY_TO_VALUE_MAP.get(ID_VALUE);

    String valueSearch = "";

    @Override
    public void initialize() {
        userService = new UserService(new UserRepository());
        bookService = new BookService(new BookRepository());
        reportService = new ReportService(new ReportRepository(), userService, bookService);
        fileService = new FileService();

        new AllSetUp().initMainAllIssuedCtrl(this);

        buttons = List.of(allButton, pendingButton, borrowedButton, returnedButton);

        ObservableList<String> displayValues = FXCollections.observableArrayList(DISPLAY_TO_VALUE_MAP.keySet());
        choiceBoxSearchFilter.setItems(displayValues);
        choiceBoxSearchFilter.setValue(ID_VALUE); // Thiết lập giá trị mặc định

        getAll();
    }

    void loadData() {
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
                buttons.get(i).getStyleClass().removeAll("selected-button");
                buttons.get(i).getStyleClass().add("unselected-button");
            } else {
                buttons.get(i).getStyleClass().removeAll("unselected-button");
                buttons.get(i).getStyleClass().add("selected-button");
            }
        }
    }

    void getAll() {
        currStatus = "";
        updateButtonStyle(0);
        listBorrowReport = reportService.findByInput(colSearch, valueSearch, currStatus);
        loadData();
    }

    void getPending() {
        currStatus = BorrowReport.PENDING;
        updateButtonStyle(1);
        listBorrowReport = reportService.findByInput(colSearch, valueSearch, currStatus);
        loadData();
    }

    void getBorrowed() {
        currStatus = BorrowReport.BORROWED;
        updateButtonStyle(2);
        listBorrowReport = reportService.findByInput(colSearch, valueSearch, currStatus);
        loadData();
    }

    void getReturned() {
        currStatus = BorrowReport.RETURNED;
        updateButtonStyle(3);
        listBorrowReport = reportService.findByInput(colSearch, valueSearch, currStatus);
        loadData();
    }

    void resetData() {
        if (currStatus == BorrowReport.PENDING) {
            getPending();
        } else if (currStatus == BorrowReport.BORROWED) {
            getBorrowed();
        } else if (currStatus == BorrowReport.RETURNED) {
            getReturned();
        } else {
            getAll();
        }
    }

}
