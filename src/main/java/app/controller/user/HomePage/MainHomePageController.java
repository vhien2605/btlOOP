package app.controller.user.HomePage;

import app.controller.BaseController;
import app.service.authService.AuthenticationService;
import app.service.mainService.ReportService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
    @FXML
    protected VBox discoverMainPage;

    protected ObservableList<String> disCoverBookSectionTitleList;

    public static AuthenticationService authService;

    public static ReportService reportService;

    private void DivideToOtherControllers() {
        new DiscoverTabController(this).initialize();
    }

    @Override
    public void initialize() {
        DivideToOtherControllers();
    }
    
    public static void getAuthService(AuthenticationService authService) {
        MainHomePageController.authService = authService;
    }
}
