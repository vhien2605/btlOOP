package app.controller.user.HomePage;

import java.security.PublicKey;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.controller.BaseController;
import app.domain.DTO.SurfaceUserDTO;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
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

    @FXML
    public Button userButton;

    @FXML
    public VBox userInfoBox;

    @FXML
    public Button signOutButton;

    @FXML
    public Button ViewProfileButton;

    @FXML
    public AnchorPane ViewUserProfilePage;

    @FXML
    public Label userProfileName;

    @FXML 
    public Label userProfileUsername;

    @FXML
    public Label userProfileAddress;

    @FXML
    public Label userProfileEmail;

    @FXML
    public Label userProfilePhoneNumber;

    @FXML
    public Button ViewProfileBackButton;

    @FXML
    public Button ChangePasswordButton;

    @FXML
    public Button changePasswordBackButton;

    @FXML
    public AnchorPane ChangePasswordPage;

    @FXML
    public Button SaveChangePasswordButton;

    @FXML
    public TextField currentPasswordField;

    @FXML
    public TextField newPasswordField;

    @FXML
    public TextField confirmNewPasswordField;

    @FXML
    public Label usernameLabelUserInfoBox;

    @FXML
    public Label emailLabelUserInfoBox;

    @FXML
    public Label helloUserLabel;

    List<Button> buttons;

    public static SurfaceUserDTO user;

    public ObservableList<String> discoverBookSectionTitleList;

    private AuthenticationService authenticationService;
    
    private DiscoverTabController discoverTabController;

    private YourBooksTabController yourBooksTabController;

    private SearchTabController searchTabController;

    private UserInfoBoxController userInfoBoxController;

    private ViewProfileController viewProfileController;

    private ChangePasswordController changePasswordController;

    private void DivideToOtherControllers() {
        discoverTabController = new DiscoverTabController(this);
        discoverTabController.initialize();

        yourBooksTabController = new YourBooksTabController(this);
        yourBooksTabController.initialize();

        searchTabController = new SearchTabController(this);
        searchTabController.initialize();

        userInfoBoxController = new UserInfoBoxController(this);
        userInfoBoxController.initialize();

        viewProfileController = new ViewProfileController(this);
        viewProfileController.initialize();

        changePasswordController = new ChangePasswordController(this);
        changePasswordController.initialize();
    }

    @Override
    public void initialize() {
        GetUserInfo();
        DivideToOtherControllers();
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
        } else if (e.getSource() == userButton) {
            userInfoBoxController.handleChangeStateUserBox();
        } else if (e.getSource() == signOutButton) {
            userInfoBoxController.handleSignOut();
        } else if (e.getSource() == ViewProfileBackButton) {
            viewProfileController.handleBackButton();
        } else if (e.getSource() == ViewProfileButton) {
            viewProfileController.handleViewProfilePage();
        } else if (e.getSource() == ChangePasswordButton) {
            changePasswordController.handleChangePasswordButtonIsclicked();
        } else if (e.getSource() == changePasswordBackButton) {
            changePasswordController.handleChangePasswordBackButton();
        } else if (e.getSource() == SaveChangePasswordButton) {
            changePasswordController.handleSaveChangePassword();
        }
    }
    
    private void GetUserInfo() {
        authenticationService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        try {
            user = authenticationService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        helloUserLabel.setText("Hello, " + user.getName());
    }
    
}
