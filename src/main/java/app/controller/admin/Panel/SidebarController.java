package app.controller.admin.Panel;

import java.util.List;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.service.authService.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController implements BaseController {
    @FXML
    private Button homeButton;

    @FXML
    private Button booksButton;

    @FXML
    private Button usersButton;

    @FXML
    private Button issueBooksButton;

    @FXML
    private Button allIssueBooksButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button signOutButton;

    List<Button> buttons;

    private static int HOME_BUTTON = 0;
    private static int BOOK_BUTTON = 1;
    private static int USER_BUTTON = 2;
    private static int ISSUEBOOK_BUTTON = 3;
    private static int ALL_ISSUEBOOK_BUTTON = 4;
    private static int currButton = HOME_BUTTON;

    private ShowAlert showAlert;
    private SessionService sessionService;

    /**
     * Click button event
     */
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == homeButton) {
            currButton = HOME_BUTTON;
            new ChangeTabController().homeTab();
        } else if (e.getSource() == booksButton) {
            currButton = BOOK_BUTTON;
            new ChangeTabController().bookTab();
        } else if (e.getSource() == usersButton) {
            currButton = USER_BUTTON;
            new ChangeTabController().userTab();
        } else if (e.getSource() == issueBooksButton) {
            currButton = ISSUEBOOK_BUTTON;
            new ChangeTabController().issueBookTab();
        } else if (e.getSource() == allIssueBooksButton) {
            currButton = ALL_ISSUEBOOK_BUTTON;
            new ChangeTabController().allIssueBookTab();
        } else if (e.getSource() == settingsButton) {
            new ChangeTabController().settingTab();
        } else if (e.getSource() == signOutButton) {
            currButton = HOME_BUTTON;
            showAlert.showAlert("Log out successfully!", "success");
            sessionService.clearWhenLogout();
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    @Override
    public void initialize() {
        buttons = List.of(homeButton, booksButton, usersButton,
                issueBooksButton, allIssueBooksButton, settingsButton);
        showAlert = new ShowAlert();
        sessionService = new SessionService();
        setStateButton();
    }

    void setStateButton() {
        buttons.get(currButton).getStyleClass().add("curr-button");
    }
}
