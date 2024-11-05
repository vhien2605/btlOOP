package app.controller.admin.AllIssueBookTab;

import app.controller.BaseController;
import app.domain.BorrowReport;
import app.repository.ReportRepository;
import app.service.mainService.ReportService;
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
    Button allButton, pendingButton, borowButton, returnButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentVBox;

    ReportService reportService;

    ObservableList<BorrowReport> listBorrowReport;

    @Override
    public void initialize() {
        reportService = new ReportService(new ReportRepository());
        loadData();
    }

    private void loadData() {
        listBorrowReport = reportService.getAllReports();

        for (BorrowReport data : listBorrowReport) {
            try {
                String path = "/view/admin/allIssueBookTab/issued_row.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Pane pane = loader.load();

                MainIssuedRowController rowController = loader.getController();
                rowController.loadData(data);

                contentVBox.getChildren().add(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
