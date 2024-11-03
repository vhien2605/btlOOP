package app.controller.admin.UserTab;

public class ImportDataController {
    final MainUserController mainUserCtrl;

    public ImportDataController(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.importDataButton.setOnAction(e -> importData());
    }

    void importData() {
        System.out.println("click button import data");
    }
}
