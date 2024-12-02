package app.controller.user.HomePage;

import java.io.File;
import java.nio.file.Paths;
import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.EditImageView;
import app.controller.user.BookLoan.BookLoanController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CardReport implements BaseController {
    @FXML
    private Button cardButton;

    @FXML
    private ImageView imageURL;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    @FXML
    private Label pendingLabel;

    @FXML
    private Label borrowingLabel;

    @FXML
    private Label returnedLabel;

    private BorrowReport borrowReport;

    private Book book;

    private String status;

    private BookService bookService;

    public void loadCardReport(BorrowReport borrowReport) {
        this.borrowReport = borrowReport;
        this.book = bookService.findByISBN(borrowReport.getBookId());
        this.status = borrowReport.getStatus();
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        loadImage(book);
        setStatus();
    }

    private void loadImage(Book book) {
        imageURL.setPreserveRatio(false);
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
            imageURL.setPreserveRatio(false);
            EditImageView.border(imageURL, 10);
            imageURL.setImage(image);
        } catch (Exception e) {
            System.out.println("Load image fail");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void setStatus() {
        pendingLabel.setVisible(false);
        borrowingLabel.setVisible(false);
        returnedLabel.setVisible(false);
        if (status.equals(BorrowReport.PENDING)) {
            pendingLabel.setVisible(true);
        } else if (status.equals(BorrowReport.BORROWED)) {
            borrowingLabel.setVisible(true);
        } else if (status.equals(BorrowReport.RETURNED)) {
            returnedLabel.setVisible(true);
        }
    }

    public void handleCardEvent(ActionEvent e) {
        if (e.getSource() == cardButton) {
            loadBookLoan();
        }
    }

    private void loadBookLoan() {
        FXMLResolver.getInstance().renderScene("user/bookloan/bookloan");
        BookLoanController controller = FXMLResolver.getInstance().getLoader().getController();
        controller.LoadBookLoan(book, borrowReport, status);
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
    }

}
