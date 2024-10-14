package app.controller.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.BookService;
import app.service.GoogleApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateBookController implements BaseController {
    @FXML
    private TextField bookISBNTextField, bookNameTextField, bookAuthorTextField, bookQuantityTextField,
            bookPublisherTextField, bookCategoryTextField, fileSeletedTextField;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button comeBackButton, cancelButton, saveButton, uploadFileButton, findDocomentButton;

    private BookService bookService;
    private GoogleApiService googleApiService;
    private static String API_KEY;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            bookService.handleSaveBook(getBook());
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == findDocomentButton) {
            searchBooks();
        } else if (e.getSource() == uploadFileButton) {
            System.out.println("Click button uploadFileButton");
        }
    }

    private void searchBooks() {
        String query = bookISBNTextField.getText();
        if (query.isEmpty()) {
            return;
        }

        String formattedQuery = query.trim().replace(" ", "+");

        // API URL với từ khóa tìm kiếm và API Key
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + formattedQuery + "&maxResults=1&key=" + API_KEY;

        // Sử dụng HttpClient để gửi yêu cầu
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(googleApiService::parseBookInfo)
                .thenAccept(books -> {
                    if (books == null) {
                        System.out.println("khong tim thay ban ghi");
                        // làm cái hiện thông báo...
                        return;
                    }

                    for (Book book : books) {
                        setTextFields(book);
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    // sau làm hiện thông báo bị lỗi...
                    return null;
                });
    }

    private Book getBook() {
        Book book = new Book(
                bookISBNTextField.getText(),
                bookNameTextField.getText(),
                bookAuthorTextField.getText(),
                bookDescriptionTextArea.getText(),
                bookCategoryTextField.getText(),
                bookPublisherTextField.getText(),
                Integer.valueOf(bookQuantityTextField.getText()),
                Integer.valueOf(bookQuantityTextField.getText()),
                "");

        return book;
    }

    private void setTextFields(Book book) {
        bookISBNTextField.setText(book.getId());
        bookNameTextField.setText(book.getName());
        bookAuthorTextField.setText(book.getAuthor());
        bookPublisherTextField.setText(book.getBookPublisher());
        bookCategoryTextField.setText(book.getCategory());
        bookDescriptionTextArea.setText(book.getDescription());
    }

    @FXML
    private void clearFields() {
        bookISBNTextField.clear();
        bookNameTextField.clear();
        bookAuthorTextField.clear();
        bookQuantityTextField.clear();
        bookPublisherTextField.clear();
        bookCategoryTextField.clear();
        bookDescriptionTextArea.clear();
    }

    private void getAPI_KEY() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }

            prop.load(input);

            API_KEY = prop.getProperty("API_KEY");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        googleApiService = new GoogleApiService();
        getAPI_KEY();
    }
}
