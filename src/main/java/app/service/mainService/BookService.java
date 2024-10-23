package app.service.mainService;

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
    public void handleSaveBook(Book book) {
        this.bookRepository.save(book);
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
     * Delete book by id
     *
     * @param id id of book selected from table to delete
     */
    public boolean deleteBook(String id) {
        return this.bookRepository.deleteById(id);
    }
}
