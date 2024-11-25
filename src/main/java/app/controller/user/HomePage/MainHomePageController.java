package app.controller.user.HomePage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.controller.BaseController;
import app.domain.DTO.SurfaceUserDTO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    public Label clockLabel;

    @FXML
    public TextField searchBoxTextField;

    @FXML
    public ChoiceBox<String> choiceBoxSearchFilter;

    @FXML
    public TilePane searchBooksMainPage;

    List<Button> buttons;

    public static SurfaceUserDTO user;

    public ObservableList<String> discoverBookSectionTitleList;
    
    private DiscoverTabController discoverTabController;

    private YourBooksTabController yourBooksTabController;

    private UserButtonController userButtonController;

    private DashboardTabController dashboardTabController;

    private SearchTabController searchTabController;

    private void DivideToOtherControllers() {
        discoverTabController = new DiscoverTabController(this);
        discoverTabController.initialize();

        yourBooksTabController = new YourBooksTabController(this);
        yourBooksTabController.initialize();

        dashboardTabController = new DashboardTabController(this);
        dashboardTabController.initialize();

        searchTabController = new SearchTabController(this);
        searchTabController.initialize();

        userButtonController = new UserButtonController(this);
        userButtonController.initialize();
    }

    @Override
    public void initialize() {
        DivideToOtherControllers();
        GetUserInfo();
        buttons = List.of(allButtonYourBooks, pendingButtonYourBooks, borrowingButtonYourBooks, returnedButtonYourBooks);
        updateButtonStyle(0);
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

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == allButtonYourBooks) {
            updateButtonStyle(0);
            yourBooksTabController.handleAllButtonIsClicked();
        } else if (e.getSource() == pendingButtonYourBooks) {
            updateButtonStyle(1);
            yourBooksTabController.handlePendingButtonIsClicked();
        } else if (e.getSource() == borrowingButtonYourBooks) {
            updateButtonStyle(2);
            yourBooksTabController.handleBorrowingButtonIsClicked();
        } else if (e.getSource() == returnedButtonYourBooks) {
            updateButtonStyle(3);
            yourBooksTabController.handleReturnedButtonIsClicked();
        }
    }
    
    private void GetUserInfo() {
        user = yourBooksTabController.user;
    }
    
}
