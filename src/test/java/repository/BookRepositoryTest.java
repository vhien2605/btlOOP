package repository;

import app.domain.Book;
import app.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    public void initialize() {
        System.setProperty("db.config", "database-testing.properties");
        bookRepository = new BookRepository();
    }

    @Test
    public void testFindAll() {
        List<Book> result = bookRepository.findAll();
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    public void testFindById1() {
        Optional<Book> result = bookRepository.findById("1");
        Assertions.assertNotNull(result.get());
    }

    @Test
    public void testFindById2() {
        Optional<Book> result = bookRepository.findById("2");
        Assertions.assertNotNull(result.get());
    }

    @Test
    public void testFindById3() {
        Optional<Book> result = bookRepository.findById("");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testCount() {
        int count = bookRepository.count();
        Assertions.assertEquals(5, count);
    }

    @Test
    public void findByCategory() {
        List<Book> books = bookRepository.findByCategory("Fiction");
        Assertions.assertEquals(1, books.size());
    }

    @Test
    public void findByCategory2() {
        List<Book> books = bookRepository.findByCategory("Science");
        Assertions.assertEquals(0, books.size());
    }

    @Test
    public void updateOneTest() {
        Book newBook = new Book("9780471461494", "Advances in Chemical Physics", "Ilya Prigogine, Stuart A. Rice", "", "Science",
                "John Wiley & Sons", 120, 120, "");
        Assertions.assertTrue(this.bookRepository.updateOne(newBook));
    }


    @Test
    public void findByInputTest() {
        List<Book> alls = this.bookRepository.findByInput("author", "Harper Lee");
        Assertions.assertEquals(1, alls.size());
    }
}
