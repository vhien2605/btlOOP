module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.slf4j;
    requires javafx.base;
    requires javafx.graphics;
    requires java.compiler;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires java.desktop;

    opens app.controller to javafx.fxml;
    opens app.controller.admin.Panel to javafx.fxml;
    opens app.controller.admin.BookTab to javafx.fxml;
    opens app.controller.admin.BookTab.CreateBookTab to javafx.fxml;
    opens app.controller.admin.BookTab.UpdateBookTab to javafx.fxml;
    opens app.controller.admin.HomeTab to javafx.fxml;
    opens app.controller.admin.IssueBookTab to javafx.fxml;
    opens app.controller.admin.AllIssueBookTab to javafx.fxml;
    opens app.controller.admin.SettingTab to javafx.fxml;
    opens app.controller.admin.UserTab to javafx.fxml;
    opens app.controller.user.HomePage to javafx.fxml;
    opens app.controller.user.BookDetail to javafx.fxml;
    opens app.controller.admin.BookLoanTab to javafx.fxml;
    opens app.domain to javafx.base;
    opens app.controller.auth to javafx.fxml;

    exports app;
    exports app.repository;
    exports app.domain;
}
