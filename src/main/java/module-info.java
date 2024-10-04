module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens app.controller to javafx.fxml;
    opens app.controller.Panel to javafx.fxml;
    opens app.controller.BookTab to javafx.fxml;
    opens app.controller.HomeTab to javafx.fxml;
    opens app.controller.IssueBookTab to javafx.fxml;
    opens app.controller.ReturnBookTab to javafx.fxml;
    opens app.controller.SettingTab to javafx.fxml;
    opens app.controller.StudentTab to javafx.fxml;

    exports app;
}
