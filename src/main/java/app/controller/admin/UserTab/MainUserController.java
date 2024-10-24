package app.controller.admin.UserTab;

import app.controller.BaseController;
import app.domain.User;
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
    Button cencelButton, insertButton, updateButton, deleteButton, importDataButton;

    private UserService userService;

    ObservableList<User> listUser;

    @Override
    public void initialize() {
        userService = new UserService(new UserRepository());
        showUsers();
        getSelectedUser();
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
        if (e.getSource() == cencelButton) {
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

    private void addNewUser() {
        User user = getUser();
        // userService.handleAddNewUser(user);
        clearTextFields();
        showUsers();
    }

    private void updateUser() {
        User user = getUser();
    }

    private void deleteUser() {
        User user = getUser();
    }

    private void showUsers() {
        // listUser = userService.getAllUsers();
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
    }

    private void setTextFields(User user) {
        userIdTextField.setText(user.getId());
        fullNameTextField.setText(user.getName());
        addressTextField.setText(user.getAddress());
        emailTextField.setText(user.getEmail());
        phoneNumberTextField.setText(user.getPhoneNumber());
    }
}
