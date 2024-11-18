package app.controller.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {
    public static final String error = "ERROR";

    public void showAlert(String message, String type) {
        Alert alert;
        if (type.equals("error")) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } else if (type.equals("success")) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}
