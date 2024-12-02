package app.controller.user.HomePage;


import app.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CommentController implements BaseController {
    @FXML
    private TextArea commentTextArea;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label commentDateLabel;

    public void loadComment(String username, String commentText, String date) {
        usernameLabel.setText(username);
        commentTextArea.setText(commentText);
        commentDateLabel.setText(date);
    }

    @Override
    public void initialize() {

    }

}
