package app.controller.Panel;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;

public class ChangeTabController implements BaseController {
    public void homeTab() {
        FXMLResolver.getInstance().renderScene("home_tab");
    }

    public void bookTab() {
        FXMLResolver.getInstance().renderScene("bookTab/book_tab");
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

    @Override
    public void initialize() {
        
    }
}
