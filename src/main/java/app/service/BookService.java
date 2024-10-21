package app.service;

import app.domain.Book;
import app.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ObservableList<Book> getAllBooks() {
        List<Book> listOfBooks = this.bookRepository.findAll();
        return FXCollections.observableList(listOfBooks);
    }

    public void handleSaveBook(Book book) {
        this.bookRepository.save(book);
    }
}
