package app.controller.admin.UserTab;

import app.controller.BaseController;
import app.domain.User;
import app.helper.ShowAlert;
import app.repository.UserRepository;
import app.service.mainService.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainUserController implements BaseController {

    @FXML
    TableView<User> userTableView;

    @FXML
    TableColumn<User, String> userIdCol;

    @FXML
    TableColumn<User, String> fullNameCol;

    @FXML
    TableColumn<User, String> phoneNumberCol;

    @FXML
    TableColumn<User, String> emailCol;

    @FXML
    TableColumn<User, String> addressCol;

    @FXML
    TextField userIdTextField, fullNameTextField, phoneNumberTextField, emailTextField,
            addressTextField, searchUserTextField;

    @FXML
    Button cancelButton, insertButton, updateButton, deleteButton, importDataButton;

    private UserService userService;

    private ShowAlert showAlert;

    ObservableList<User> listUser;

    private static final int ADD_NEW = 1;
    private static final int UPDATE_AND_DELETE = 2;

    @Override
    public void initialize() {
        userService = new UserService(new UserRepository());
        showAlert = new ShowAlert();
        showUsers();
        getSelectedUser();
        setCanClickButton(ADD_NEW);
    }

    private void getSelectedUser() {
        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    setTextFields(newValue);
                }
            }
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            clearTextFields();
        } else if (e.getSource() == insertButton) {
            addNewUser();
        } else if (e.getSource() == updateButton) {
            updateUser();
        } else if (e.getSource() == deleteButton) {
            deleteUser();
        } else if (e.getSource() == importDataButton) {
            System.out.println("click button import data");
        }
    }

    private void setCanClickButton(int type) {
        cancelButton.setStyle("-fx-background-color: lightblue;");
        if (type == 1) {
            insertButton.setDisable(false);
            insertButton.setStyle("-fx-background-color: lightblue;");
            updateButton.setDisable(true);
            updateButton.setStyle("-fx-background-color: lightgray;");
            deleteButton.setDisable(true);
            deleteButton.setStyle("-fx-background-color: lightgray;");
        } else if (type == 2) {
            insertButton.setDisable(true);
            insertButton.setStyle("-fx-background-color: lightgray;");
            updateButton.setDisable(false);
            updateButton.setStyle("-fx-background-color: lightblue;");
            deleteButton.setDisable(false);
            deleteButton.setStyle("-fx-background-color: lightblue;");
        }
    }

    private void addNewUser() {
        User user = getUser();

        if (user == null) {
            return;
        }

        if (userService.handleSaveUser(user)) {
            clearTextFields();
            listUser.add(user);
            showAlert.showAlert("Add new user successed!", "success");
        } else {
            showAlert.showAlert("Add new user failed!", "error");
        }

    }

    private void updateUser() {
        User user = getUser();

        if (user == null) {
            return;
        }

        if (userService.handleUpdateOne(user)) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getId().equals(user.getId())) {
                    listUser.set(i, user);
                    break;
                }
            }
            showAlert.showAlert("Update user successed!", "success");
        } else {
            showAlert.showAlert("Update user failed!", "error");
        }
    }

    private void deleteUser() {
        User user = getUser();

        if (user == null) {
            return;
        }

        String id = user.getId();
        if (userService.deleteUser(id)) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getId().equals(id)) {
                    listUser.remove(i);
                    break;
                }
            }
            clearTextFields();
            showAlert.showAlert("Delete user successed!", "success");
        } else {
            showAlert.showAlert("Delete user failed!", "error");
        }
    }

    private void showUsers() {
        listUser = userService.getAllUsers();
        // Thiết lập các cột cho TableView
        userIdCol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));

        // Đặt danh sách vào TableView
        userTableView.setItems(listUser);
    }

    private User getUser() {
        if (!validateFields()) {
            return null;
        }

        return new User(userIdTextField.getText(),
                userIdTextField.getText(),
                userIdTextField.getText(),
                "USER",
                fullNameTextField.getText(),
                addressTextField.getText(),
                emailTextField.getText(),
                phoneNumberTextField.getText());
    }

    private void clearTextFields() {
        userIdTextField.clear();
        fullNameTextField.clear();
        addressTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
        setCanClickButton(ADD_NEW);
    }

    private void setTextFields(User user) {
        userIdTextField.setText(user.getId());
        fullNameTextField.setText(user.getName());
        addressTextField.setText(user.getAddress());
        emailTextField.setText(user.getEmail());
        phoneNumberTextField.setText(user.getPhoneNumber());
        setCanClickButton(UPDATE_AND_DELETE);
    }

    private boolean validateFields() {
        // Check userId
        if (userIdTextField.getText().isEmpty()) {
            showAlert.showAlert("User ID không được để trống!", "error");
            return false;
        }

        // Check fullName
        if (fullNameTextField.getText().isEmpty()) {
            showAlert.showAlert("Họ tên không được để trống!", "error");
            return false;
        }

        // Check phoneNumber
        String phoneNumber = phoneNumberTextField.getText();
        if (phoneNumber.isEmpty() || !phoneNumber.matches("\\d{10}")) {
            showAlert.showAlert("Số điện thoại không hợp lệ!", "error");
            return false;
        }

        // Check email
        String email = emailTextField.getText();
        if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert.showAlert("Địa chỉ email không hợp lệ!", "error");
            return false;
        }

        // Check address
        if (addressTextField.getText().isEmpty()) {
            showAlert.showAlert("Địa chỉ không được để trống!", "error");
            return false;
        }

        return true;
    }

}
