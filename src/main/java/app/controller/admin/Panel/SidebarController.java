package app.controller.admin.Panel;

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

    /**
     * Click button event
     */
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == homeButton) {
            new ChangeTabController().homeTab();
        } else if (e.getSource() == booksButton) {
            new ChangeTabController().bookTab();
        } else if (e.getSource() == usersButton) {
            new ChangeTabController().userTab();
        } else if (e.getSource() == issueBooksButton) {
            new ChangeTabController().issueBookTab();
        } else if (e.getSource() == allIssueBooksButton) {
            new ChangeTabController().allIssueBookTab();
        } else if (e.getSource() == settingsButton) {
            new ChangeTabController().settingTab();
        }
    }

    @Override
    public void initialize() {

    }
}
