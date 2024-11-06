package app.controller.admin.Panel;

import java.util.List;

import app.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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

    @FXML
    private Pane root;

    List<Button> buttons;

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

    public void setStateButton(int indexButton) {
        buttons.get(indexButton).getStyleClass().add("curr-button");
    }

    @Override
    public void initialize() {
        root.getProperties().put("controller", this);

        buttons = List.of(homeButton, booksButton, usersButton,
                issueBooksButton, allIssueBooksButton, settingsButton);
    }
}
