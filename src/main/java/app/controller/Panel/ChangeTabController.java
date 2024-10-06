package app.controller.Panel;

import app.config.ViewConfig.FXMLResolver;

public class ChangeTabController {
    public void homeTab() {
        FXMLResolver.getInstance().renderScene("home_tab");
    }

    public void bookTab() {
        FXMLResolver.getInstance().renderScene("book_tab");
    }

    public void issueBookTab() {
        FXMLResolver.getInstance().renderScene("issuebook_tab");
    }

    public void returnBookTab() {
        FXMLResolver.getInstance().renderScene("returnbook_tab");
    }

    public void studentTab() {
        FXMLResolver.getInstance().renderScene("student_tab");
    }

    public void settingTab() {
        FXMLResolver.getInstance().renderScene("setting_tab");
    }

}
