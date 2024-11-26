package service;

import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooksTest() {
        //config action simulator for bookRepository
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("1", "Head first java", "Author1", "sach dep trai",
                "Sex gay", "Kim dong", 120, 120, "dshadsahds"));
        mockBooks.add(new Book("2", "Head first design pattern", "Author2", "sach xau trai",
                "Sex gay", "Kim dong", 140, 120, "dshadsahds"));
        Mockito.when(bookRepository.findAll()).thenReturn(mockBooks);
        ObservableList<Book> result = bookService.getAllBooks();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testUpdateBookService() {
        Book newBook = new Book("9780471461494", "Advances in Chemical Physics", "Ilya Prigogine, Stuart A. Rice", "", "Science",
                "John Wiley & Sons", 120, 120, "");
        Mockito.when(bookRepository.updateOne(newBook)).thenReturn(true);
        Assertions.assertTrue(bookService.handleUpdateOne(newBook));
    }
}
