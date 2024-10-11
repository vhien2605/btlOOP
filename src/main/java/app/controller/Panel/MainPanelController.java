package app.controller.Panel;

import app.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPanelController implements BaseController {
    @FXML
    private Button homeButton;

    @FXML
    private Button docomentsButton;

    @FXML
    private Button studentsButton;

    @FXML
    private Button issueBooksButton;

    @FXML
    private Button returnBooksButton;

    @FXML
    private Button settingsButton;

    private Button focused_button = null;

    /**
     * Click button event
     */
    @FXML
    private void handdleButtonAction(ActionEvent e) {
        if (e.getSource() == homeButton) {
            new ChangeTabController().homeTab();
        } else if (e.getSource() == docomentsButton) {
            new ChangeTabController().bookTab();
        } else if (e.getSource() == studentsButton) {
            new ChangeTabController().studentTab();
        } else if (e.getSource() == issueBooksButton) {
            new ChangeTabController().issueBookTab();
        } else if (e.getSource() == returnBooksButton) {
            new ChangeTabController().returnBookTab();
        } else if (e.getSource() == settingsButton) {
            new ChangeTabController().settingTab();
        }
    }

    @Override
    public void initialize() {

    }
}
