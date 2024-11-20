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
    requires mysql.connector.java;

    requires kernel;
    requires layout;
    requires io;
    requires javafx.swing;
    
    requires cloudinary.core;

    requires com.google.api.client;
    requires com.google.api.services.gmail;
    requires com.google.gson;
    requires google.api.client;
    requires com.google.api.client.json.gson;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires mail;
    requires jdk.httpserver;
    requires activation;

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
