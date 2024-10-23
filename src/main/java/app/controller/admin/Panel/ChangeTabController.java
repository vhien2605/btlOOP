package app.controller.admin.Panel;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;

public class ChangeTabController implements BaseController {
    public void homeTab() {
        FXMLResolver.getInstance().renderScene("homeTab/home_tab");
    }

    public void bookTab() {
        FXMLResolver.getInstance().renderScene("bookTab/book_tab");
    }

    public void issueBookTab() {
        FXMLResolver.getInstance().renderScene("issueBookTab/issuebook_tab");
    }

    public void returnBookTab() {
        FXMLResolver.getInstance().renderScene("returnBookTab/returnbook_tab");
    }

    public void userTab() {
        FXMLResolver.getInstance().renderScene("userTab/user_tab");
    }

    public void settingTab() {
        FXMLResolver.getInstance().renderScene("settingTab/setting_tab");
    }

    @Override
    public void initialize() {

    }
}
