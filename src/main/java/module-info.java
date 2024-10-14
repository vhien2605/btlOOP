module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.json;

    opens app.controller to javafx.fxml;
    opens app.controller.Panel to javafx.fxml;
    opens app.controller.BookTab to javafx.fxml;
    opens app.controller.HomeTab to javafx.fxml;
    opens app.controller.IssueBookTab to javafx.fxml;
    opens app.controller.ReturnBookTab to javafx.fxml;
    opens app.controller.SettingTab to javafx.fxml;
    opens app.controller.StudentTab to javafx.fxml;
    opens app.domain to javafx.base;

    exports app;
}
