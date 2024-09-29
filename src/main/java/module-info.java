module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens app.controller to javafx.fxml;

    exports app;
}
