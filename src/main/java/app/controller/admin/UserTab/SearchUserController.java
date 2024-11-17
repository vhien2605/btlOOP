package app.controller.admin.UserTab;

import javafx.scene.input.KeyCode;

public class SearchUserController {
    final MainUserController mainUserCtrl;

    public SearchUserController(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.searchBoxTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchUser();
            }
        });

        mainUserCtrl.choiceBoxSearchFilter.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchUser();
                });

    }

    private void searchUser() {
        String col = MainUserController.DISPLAY_TO_VALUE_MAP.get(mainUserCtrl.choiceBoxSearchFilter.getValue());
        String value = mainUserCtrl.searchBoxTextField.getText();

        System.out.println(col + " LIKE %" + value + "%");

        mainUserCtrl.listUser = mainUserCtrl.userService.search(col, value);
        mainUserCtrl.userTableView.setItems(mainUserCtrl.listUser);
    }
}
