package app.controller.admin.Panel;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;

public class ChangeTabController implements BaseController {
    public void homeTab() {
        FXMLResolver.getInstance().renderScene("admin/homeTab/home_tab");
    }

    public void bookTab() {
        FXMLResolver.getInstance().renderScene("admin/bookTab/book_tab");
    }

    public void issueBookTab() {
        FXMLResolver.getInstance().renderScene("admin/issueBookTab/issuebook_tab");
    }

    public void allIssueBookTab() {
        FXMLResolver.getInstance().renderScene("admin/allIssueBookTab/all_issuebook_tab");
    }

    public void userTab() {
        FXMLResolver.getInstance().renderScene("admin/userTab/user_tab");
    }

    public void settingTab() {
        FXMLResolver.getInstance().renderScene("admin/settingTab/setting_tab");
    }

    @Override
    public void initialize() {

    }
}
