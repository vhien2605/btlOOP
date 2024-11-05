package app.controller.admin.AllIssueBookTab;

import app.controller.BaseController;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainAllIssueController implements BaseController {
    @FXML
    TextField searchTextFiled;

    @FXML
    Button allButton, pendingButton, borowButton, returnButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentVBox;

    ReportService reportService;

    UserService userService;

    BookService bookService;

    @Override
    public void initialize() {
        loadData();
    }

    private void loadData() {
        for (int i = 1; i <= 20; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/view/admin/allIssueBookTab/issued_row.fxml"));
                Pane pane = loader.load();

                contentVBox.getChildren().add(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
