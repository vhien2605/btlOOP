package app.controller.admin.UserTab;

import java.util.LinkedHashMap;
import java.util.Map;

import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.User;
import app.repository.UserRepository;
import app.service.mainService.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
            addressTextField, searchBoxTextField;

    @FXML
    ChoiceBox<String> choiceBoxSearchFilter;

    @FXML
    Button cancelButton, insertButton, updateButton, deleteButton, importDataButton;

    UserService userService;

    ShowAlert showAlert;

    ObservableList<User> listUser;

    static final int ADD_NEW = 1;
    static final int UPDATE_AND_DELETE = 2;

    static final String USER_ID_VALUE = "User id";
    static final String NAME_VALUE = "Name";
    static final String EMAIL_VALUE = "Email";
    static final String USERNAME_VALUE = "Username";

    static final Map<String, String> DISPLAY_TO_VALUE_MAP = new LinkedHashMap<>();
    static {
        DISPLAY_TO_VALUE_MAP.put(USER_ID_VALUE, "id");
        DISPLAY_TO_VALUE_MAP.put(NAME_VALUE, "name");
        DISPLAY_TO_VALUE_MAP.put(EMAIL_VALUE, "email");
        DISPLAY_TO_VALUE_MAP.put(USERNAME_VALUE, "username");
    }

    @Override
    public void initialize() {
        userService = new UserService(new UserRepository());
        showAlert = new ShowAlert();
        showUsers();
        getSelectedUser();
        setCanClickButton(ADD_NEW);
        new AllSetUp().init_function(this);

        ObservableList<String> displayValues = FXCollections.observableArrayList(DISPLAY_TO_VALUE_MAP.keySet());
        choiceBoxSearchFilter.setItems(displayValues);
        choiceBoxSearchFilter.setValue(NAME_VALUE); // Thiết lập giá trị mặc định
    }

    void getSelectedUser() {
        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    setTextFields(newValue);
                }
            }
        });
    }

    void setCanClickButton(int type) {
        cancelButton.setStyle("-fx-background-color: lightblue; -fx-cursor: hand;");
        if (type == ADD_NEW) {
            insertButton.setDisable(false);
            userIdTextField.setEditable(true);
            insertButton.setStyle("-fx-background-color: lightblue; -fx-cursor: hand;");
            updateButton.setDisable(true);
            updateButton.setStyle("-fx-background-color: lightgray; -fx-cursor: hand;");
            deleteButton.setDisable(true);
            deleteButton.setStyle("-fx-background-color: lightgray; -fx-cursor: hand;");
        } else if (type == UPDATE_AND_DELETE) {
            insertButton.setDisable(true);
            userIdTextField.setEditable(false);
            insertButton.setStyle("-fx-background-color: lightgray; -fx-cursor: hand;");
            updateButton.setDisable(false);
            updateButton.setStyle("-fx-background-color: lightblue; -fx-cursor: hand;");
            deleteButton.setDisable(false);
            deleteButton.setStyle("-fx-background-color: lightblue; -fx-cursor: hand;");
        }
    }

    void showUsers() {
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

    User getUser() {
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

    void clearTextFields() {
        userIdTextField.clear();
        fullNameTextField.clear();
        addressTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
        setCanClickButton(ADD_NEW);
    }

    void setTextFields(User user) {
        userIdTextField.setText(user.getId());
        fullNameTextField.setText(user.getName());
        addressTextField.setText(user.getAddress());
        emailTextField.setText(user.getEmail());
        phoneNumberTextField.setText(user.getPhoneNumber());
        setCanClickButton(UPDATE_AND_DELETE);
    }

    boolean validateFields() {
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
