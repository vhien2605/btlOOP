module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.slf4j;
    requires javafx.base;

    opens app.controller to javafx.fxml;
    opens app.controller.admin.Panel to javafx.fxml;
    opens app.controller.admin.BookTab to javafx.fxml;
    opens app.controller.admin.BookTab.CreateBookTab to javafx.fxml;
    opens app.controller.admin.BookTab.UpdateBookTab to javafx.fxml;
    opens app.controller.admin.HomeTab to javafx.fxml;
    opens app.controller.admin.IssueBookTab to javafx.fxml;
    opens app.controller.admin.ReturnBookTab to javafx.fxml;
    opens app.controller.admin.SettingTab to javafx.fxml;
    opens app.controller.admin.UserTab to javafx.fxml;
    opens app.domain to javafx.base;

    exports app;
    exports app.repository;
    exports app.domain;
}
