package app.service.mainService;

import java.util.List;
import java.util.Optional;

import app.domain.Book;
import app.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for all {@link Book} logic.
 * <p>
 * {@link BookService } for doing all logic business with {@link Book} entity
 */
public class BookService {
    private final BookRepository bookRepository;

    /**
     * inject Repository instance by constructor by Dependency Injection design
     * pattern.
     *
     * @param bookRepository {@link Book} accessData logic
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Get all book method, call to {@code BookRepository}.
     *
     * @return {@code ObservableList<Book>} send to front-end
     */
    public ObservableList<Book> getAllBooks() {
        return FXCollections.observableList(this.bookRepository.findAll());
    }

    /**
     * Handle save {@link Book} logic.
     *
     * @param book {@link Book} entity want to save to database
     */
    public boolean handleSaveBook(Book book) {
        return this.bookRepository.save(book);
    }

    /**
     * Service find by ISBN.
     *
     * @param bookISBN keyword for searching by ISBN
     * @return {@link Book} which has input id
     */
    public Book findByISBN(String bookISBN) {
        Optional<Book> wrapperResult = this.bookRepository.findById(bookISBN);
        return wrapperResult.orElse(null);
    }

    /**
     * Service find by category.
     *
     * @param keyword keyword for searching in category
     * @return {@code ObservableList<Book>} using for front-end layer
     */
    public ObservableList<Book> findByCategory(String keyword) {
        return FXCollections.observableList(this.bookRepository.findByCategory(keyword));
    }

    /**
     * handle update book service.
     *
     * @param book new {@link Book} to replace the current {@link Book}
     * @return {@code boolean} if update in repo success or failed
     */
    public boolean handleUpdateOne(Book book) {
        return this.bookRepository.updateOne(book);
    }

    /**
     * Delete {@link Book} method
     *
     * @param id {@link Book} id want to delete
     * @return {@code true/false} when delete success or failed
     */
    public boolean deleteBook(String id) {
        return this.bookRepository.deleteById(id);
    }
    
    public ObservableList<Book> search(String col, String value) {
        return FXCollections.observableList(this.bookRepository.findByInput(col, value));
    }
}
