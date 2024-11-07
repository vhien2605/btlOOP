package app.controller.admin.BookTab;

import java.io.File;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import app.service.subService.FileService;
import app.service.subService.GoogleApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public abstract class HandleBookController implements BaseController {
    @FXML
    protected TextField bookISBNTextField, bookNameTextField, bookAuthorTextField, bookQuantityTextField,
            bookPublisherTextField, bookCategoryTextField, imagePathTextField;

    @FXML
    protected TextArea bookDescriptionTextArea;

    @FXML
    protected Button comeBackButton, saveButton, uploadFileButton;

    protected BookService bookService;

    protected GoogleApiService googleApiService;

    protected FileService fileService;

    protected File selectedFile;

    protected ShowAlert showAlert;

    @FXML
    protected abstract void handleButtonAction(ActionEvent e);

    protected abstract void saveBook();

    protected abstract Book getBook();

    protected abstract boolean validateFields();

    protected void RenderFileDialog() {
        System.out.println("Click button uploadFileButton");
        FileChooser fileChooser = new FileChooser();
        // filter file
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.png,*.jpeg,*.jpg)",
                "*.png", "*.jpeg", "jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(FXMLResolver.getInstance().getStage());
        if (selectedFile != null) {
            imagePathTextField.setText(selectedFile.getName());
        }
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        googleApiService = new GoogleApiService();
        fileService = new FileService();
        showAlert = new ShowAlert();
    }
}
