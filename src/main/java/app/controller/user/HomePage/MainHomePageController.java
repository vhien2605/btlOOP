package app.controller.user.HomePage;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.SessionException;
import app.service.authService.AuthenticationService;
import app.service.mainService.ReportService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
    @FXML
    protected VBox discoverMainPage;

    @FXML
    protected TilePane yourBooksMainPage;

    @FXML
    protected Button allButtonYourBooks;

    @FXML
    protected Button pendingButtonYourBooks;

    @FXML 
    protected Button borrowingButtonYourBooks;

    @FXML
    protected Button returnedButtonYourBooks;

    protected ObservableList<String> discoverBookSectionTitleList;

    public static SurfaceUserDTO user;

    public static AuthenticationService authService;

    private void DivideToOtherControllers() {
        new DiscoverTabController(this).initialize();
        new YourBooksTabController(this).initialize();
    }

    @Override
    public void initialize() {
        getUserInfo();
        DivideToOtherControllers();
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == allButtonYourBooks) {
            System.out.println("All button is clicked");
        } else if (e.getSource() == pendingButtonYourBooks) {
            System.out.println("Pending button is clicked");
        } else if (e.getSource() == borrowingButtonYourBooks) {
            System.out.println("Borrowing button is clicked");
        } else if (e.getSource() == returnedButtonYourBooks) {
            System.out.println("Returned button is clicked");
        }
    }
    
    public static void getAuthService(AuthenticationService authService) {
        MainHomePageController.authService = authService;
    }

    private void getUserInfo() {
        try {
            user = authService.getCurrentUser();
        } catch (SessionException exception) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }
}
