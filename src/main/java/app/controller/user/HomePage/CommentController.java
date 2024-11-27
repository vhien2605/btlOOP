package app.controller.user.HomePage;


import app.controller.BaseController;
import app.domain.Book;
import app.domain.Comment;
import app.domain.User;
import app.repository.BookRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.mainService.BookService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CommentController implements BaseController {
    @FXML
    private TextArea commentTextArea;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label commentDateLabel;

    private UserService userService;

    private BookService bookService;

    public void loadComment(Comment comment) {
        String userID = comment.getUserId();
        User user = userService.findById(userID);
        String bookID = comment.getBookId();
        Book book = bookService.findByISBN(bookID);

        usernameLabel.setText(user.getName());
        commentTextArea.setText(comment.getInformation());
        commentDateLabel.setText(comment.getDate());
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        userService = new UserService(new UserRepository());
    }

}
