package app.controller.Panel;

import app.config.ViewConfig.FXMLResolver;

public class ChangeTabController {
    public void homeTab() {
        FXMLResolver.getInstance().renderScene("home_tab", 1280, 640);
    }

    public void bookTab() {
        FXMLResolver.getInstance().renderScene("book_tab", 1280, 640);
    }

    public void issueBookTab() {
        FXMLResolver.getInstance().renderScene("issuebook_tab", 1280, 640);
    }

    public void returnBookTab() {
        FXMLResolver.getInstance().renderScene("returnbook_tab", 1280, 640);
    }

    public void studentTab() {
        FXMLResolver.getInstance().renderScene("student_tab", 1280, 640);
    }

    public void settingTab() {
        FXMLResolver.getInstance().renderScene("setting_tab", 1280, 640);
    }

}
