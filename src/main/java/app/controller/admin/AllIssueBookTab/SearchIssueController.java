package app.controller.admin.AllIssueBookTab;

import javafx.scene.input.KeyCode;

public class SearchIssueController {
    final MainAllIssueController mainAllIssueCtrl;

    public SearchIssueController(MainAllIssueController mainAllIssueCtrl) {
        this.mainAllIssueCtrl = mainAllIssueCtrl;
    }

    void init() {
        mainAllIssueCtrl.searchBoxTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchIssue();
            }
        });

        mainAllIssueCtrl.choiceBoxSearchFilter.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchIssue();
                });

    }

    private void searchIssue() {
        String col = MainAllIssueController.DISPLAY_TO_VALUE_MAP.get(mainAllIssueCtrl.choiceBoxSearchFilter.getValue());
        String value = mainAllIssueCtrl.searchBoxTextField.getText();
        String status = mainAllIssueCtrl.currStatus;

        // Search values ​​to serve both status filtering
        mainAllIssueCtrl.colSearch = col;
        mainAllIssueCtrl.valueSearch = value;

        System.out.println(col + " LIKE %" + value + "%" + " status = " + status);

        mainAllIssueCtrl.listBorrowReport = mainAllIssueCtrl.reportService.findByInput(col, value, status);
        mainAllIssueCtrl.loadData();
    }
}
