package repository;

import app.domain.Book;
import app.repository.BookRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    public void initialize() {
        bookRepository = new BookRepository();
    }

    @Test
    public void testFindAll() {
        List<Book> result = bookRepository.findAll();
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    public void testFindById1() {
        Optional<Book> result = bookRepository.findById("9781491910740");
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals("Head First Java", result.get().getName());
    }

    @Test
    public void testFindById2() {
        Optional<Book> result = bookRepository.findById("9780596527587");
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals("Head First Statistics", result.get().getName());
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
        List<Book> books = bookRepository.findByCategory("Comic");
        Assertions.assertEquals(2, books.size());
    }

    @Test
    public void findByCategory2() {
        List<Book> books = bookRepository.findByCategory("Computers");
        Assertions.assertEquals(2, books.size());
    }

    @Test
    public void updateOneTest() {
        Book newBook = new Book("9780471461494", "Advances in Chemical Physics", "Ilya Prigogine, Stuart A. Rice", "", "Science",
                "John Wiley & Sons", 120, 120, "");
        Assertions.assertTrue(this.bookRepository.updateOne(newBook));
    }


    @Test
    public void findByInputTest() {
        List<Book> alls = this.bookRepository.findByInput("name", "Head First Java");
        Assertions.assertEquals(1, alls.size());
    }
}
