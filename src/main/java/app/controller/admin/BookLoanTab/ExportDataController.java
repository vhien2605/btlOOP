package app.controller.admin.BookLoanTab;

public class ExportDataController {
    final MainBookLoanController mainBookLoanCtrl;

    public ExportDataController(MainBookLoanController mainBookLoanCtrl) {
        this.mainBookLoanCtrl = mainBookLoanCtrl;
    }

    void init() {
        mainBookLoanCtrl.exportButton.setOnAction(e -> exportData());
    }

    void exportData() {
        System.out.println("Click button export");
    }
}
