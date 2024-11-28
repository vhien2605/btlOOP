package app.controller.user.BookDetail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.SendMailHelper;
import app.controller.helper.ShowAlert;
import app.controller.user.BookLoan.BookLoanController;
import app.controller.user.HomePage.Card;
import app.controller.user.HomePage.CommentController;
import app.controller.user.HomePage.MainHomePageController;
import app.domain.Book;
import app.domain.Comment;
import app.domain.BorrowReport;
import app.domain.DTO.CommentDTO;
import app.domain.DTO.SurfaceUserDTO;
import app.domain.User;
import app.exception.auth.SessionException;
import app.repository.CommentRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.CommentService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import app.service.subService.FileService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BookDetailController implements BaseController {
    @FXML
    private ImageView imageURL;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Button sendBorrowBookRequestButton;

    @FXML
    private DatePicker borrowDatePicker;

    @FXML
    private DatePicker expectedReturnDatePicker;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label authorNameLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    public AnchorPane borrowRequestBox;

    @FXML
    public AnchorPane pendingBox;

    @FXML
    public AnchorPane borrowingBox;

    @FXML
    private Button viewBookLoanBorrowingButton;

    @FXML
    private Button viewBookLoanPendingButton;

    @FXML
    private TextField commentTextField;

    @FXML
    private VBox commentVBoxView;

    private SurfaceUserDTO user;

    private ShowAlert showAlert;

    private ReportService reportService;

    private CommentService commentService;

    private AuthenticationService authenticationService;

    private FileService fileService;

    private Book book;

    public void loadBookWithStatus(Book book) {
        this.book = book;
        loadImage();
        loadData();
        loadCommentFromDatabase();
    }

    private void loadData() {
        bookNameLabel.setText(book.getName());
        authorNameLabel.setText(book.getAuthor());
        categoryLabel.setText(book.getCategory());
        publisherLabel.setText(book.getBookPublisher());
        descriptionArea.setText(book.getDescription());
    }

    private void loadImage() {
        if (book.getImagePath() == null) {
            return;
        }
        try {
            String rootPath = Paths.get("").toAbsolutePath().toString();
            String imagePath = Paths.get(rootPath, "src", "main", "resources", "image", "book", book.getImagePath())
                    .toAbsolutePath().toString();
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("Image file not found: " + imagePath);
                return;
            }
            String imageURI = imageFile.toURI().toString();
            Image image = new Image(imageURI);
            imageURL.setImage(image);
        } catch (Exception e) {
            System.out.println("Load image fail");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backToHomeButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        } else if (e.getSource() == sendBorrowBookRequestButton) {
            sendBorrowBookRequest();
        }
    }

    private void sendBorrowBookRequest() {
        if (!validateFields()) {
            return;
        }

        if(book.getBookRemaining() <= 0){
            showAlert.showAlert("The number of books left is not enough!", "error");
            return;
        }

        BorrowReport borrowReport = new BorrowReport(
                0,
                MainHomePageController.user.getId(),
                book.getId(),
                borrowDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                null,
                expectedReturnDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                BorrowReport.PENDING,
                ""
        );
        if (reportService.handleSave(borrowReport)) {
            showAlert.showAlert("Create new borrow book request successfully!", "success");
            createQRImage(borrowReport);
            loadBookLoan(borrowReport);
            SendMailHelper.sendMailForUser(FXMLResolver.getInstance().getLoader().getController(), user.getEmail());
        } else {
            showAlert.showAlert("Create new borrow book request fail!", "error");
        }
    }

    private void createQRImage(BorrowReport data) {
        String path = fileService.createQRImage(data, "QRcode");
        if (path != null) {
            data.setQrcodeUrl(path);
            reportService.handleUpdateOne(data);
        } else {
            System.out.println("file service return path null");
        }
    }

    private void loadBookLoan(BorrowReport borrowReport) {
        FXMLResolver.getInstance().renderScene("user/bookloan/bookloan");
        BookLoanController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.LoadBookLoan(book, borrowReport, BorrowReport.PENDING);  
    }

    private boolean validateFields() {
        if (borrowDatePicker.getValue() == null) {
            showAlert.showAlert("Borrow Date must be selected!", "error");
            return false;
        }

        if (expectedReturnDatePicker.getValue() == null) {
            showAlert.showAlert("Expected Return Date must be selected!", "error");
            return false;
        }

        if (!expectedReturnDatePicker.getValue().isAfter(borrowDatePicker.getValue())) {
            showAlert.showAlert("Expected return date must be after borrow date!", "error");
            return false;
        }

        if (book.getBookQuantity() <= 0) {
            showAlert.showAlert("This book is out of stock!", "error");
        }

        return true;
    }

    private void sendComment() {
        if (commentTextField.getText().equals("")) {
            return;
        }
        renderComment(user.getUsername(), commentTextField.getText(), LocalDate.now().toString());
        Comment comment = new Comment(0, user.getId(), book.getId(), commentTextField.getText(), LocalDate.now().toString());
        commentService.handleSaveComment(comment);
        commentTextField.clear();
    }

    private void setCommentTextFieldHandleEvent() {
        commentTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendComment();
            }
        });
    }

    private void loadCommentFromDatabase() {
        ObservableList<CommentDTO> commentList = commentService.findAllCommentDTOByBookId(book.getId());
        for (CommentDTO comment : commentList) {
            renderComment(comment.getUsername(), comment.getInformation(), comment.getDate());
        }
    }

    private void renderComment(String username, String commentText, String date) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/comment.fxml"));
            AnchorPane card = loader.load();
            commentVBoxView.getChildren().add(card);

            CommentController controller = loader.getController();
            controller.loadComment(username, commentText, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        try {
            user = authenticationService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        authenticationService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        commentService = new CommentService(new CommentRepository());
        fileService = new FileService();
        reportService = new ReportService(new ReportRepository(), null, null);
        setCommentTextFieldHandleEvent();
        getUserInfo();
    }
}
