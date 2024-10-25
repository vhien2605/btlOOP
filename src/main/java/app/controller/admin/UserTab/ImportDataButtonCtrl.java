package app.controller.admin.UserTab;

public class ImportDataButtonCtrl {
    final MainUserController mainUserCtrl;

    public ImportDataButtonCtrl(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.importDataButton.setOnAction(e -> importData());
    }

    void importData() {
        System.out.println("click button import data");
    }
}
