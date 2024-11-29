package service;

import app.domain.Book;
import app.service.subService.GoogleApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoogleApiServiceTest {
    private GoogleApiService googleApiService;

    @BeforeEach
    public void initialize() {
        googleApiService = new GoogleApiService();
    }

    @Test
    public void findBookTest() {
        String isbn = "1569319006";
        Book book = googleApiService.searchBooks(isbn).get(0);
        Assertions.assertEquals("Naruto: Awakening", book.getName());
    }

    @Test
    public void findBookTest2() {
        String isbn = "1591164419";
        Book book = googleApiService.searchBooks(isbn).get(0);
        Assertions.assertEquals("Bleach, Vol. 1", book.getName());
    }
}
