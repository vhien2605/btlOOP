package app.controller.admin.Panel;

import java.util.List;

import app.controller.BaseController;
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

    private static int currButton = 0;

    /**
     * Click button event
     */
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == homeButton) {
            currButton = 0;
            new ChangeTabController().homeTab();
        } else if (e.getSource() == booksButton) {
            currButton = 1;
            new ChangeTabController().bookTab();
        } else if (e.getSource() == usersButton) {
            currButton = 2;
            new ChangeTabController().userTab();
        } else if (e.getSource() == issueBooksButton) {
            currButton = 3;
            new ChangeTabController().issueBookTab();
        } else if (e.getSource() == allIssueBooksButton) {
            currButton = 4;
            new ChangeTabController().allIssueBookTab();
        } else if (e.getSource() == settingsButton) {
            currButton = 5;
            new ChangeTabController().settingTab();
        }
    }

    @Override
    public void initialize() {
        buttons = List.of(homeButton, booksButton, usersButton,
                issueBooksButton, allIssueBooksButton, settingsButton);
        setStateButton();
    }

    void setStateButton() {
        buttons.get(currButton).getStyleClass().add("curr-button");
    }
}
