package app.controller.user.HomePage;

import app.controller.BaseController;
import app.domain.DTO.SurfaceUserDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
    @FXML
    public ChoiceBox<String> userButton;

    @FXML
    public VBox discoverMainPage;

    @FXML
    public TilePane yourBooksMainPage;

    @FXML
    public Button allButtonYourBooks;

    @FXML
    public Button pendingButtonYourBooks;

    @FXML 
    public Button borrowingButtonYourBooks;

    @FXML
    public Button returnedButtonYourBooks;

    public static SurfaceUserDTO user;

    public ObservableList<String> discoverBookSectionTitleList;
    
    private DiscoverTabController discoverTabController;

    private YourBooksTabController yourBooksTabController;

    private UserButtonController userButtonController;

    private void DivideToOtherControllers() {
        discoverTabController = new DiscoverTabController(this);
        discoverTabController.initialize();

        yourBooksTabController = new YourBooksTabController(this);
        yourBooksTabController.initialize();

        userButtonController = new UserButtonController(this);
        userButtonController.initialize();
    }

    @Override
    public void initialize() {
        DivideToOtherControllers();
        GetUserInfo();
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == allButtonYourBooks) {
            yourBooksTabController.handleAllButtonIsClicked();
        } else if (e.getSource() == pendingButtonYourBooks) {
            yourBooksTabController.handlePendingButtonIsClicked();
        } else if (e.getSource() == borrowingButtonYourBooks) {
            yourBooksTabController.handleBorrowingButtonIsClicked();
        } else if (e.getSource() == returnedButtonYourBooks) {
            yourBooksTabController.handleReturnedButtonIsClicked();
        }
    }
    
    private void GetUserInfo() {
        user = yourBooksTabController.user;
    }
    
}
