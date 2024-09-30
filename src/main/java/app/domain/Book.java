package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty author;
    private SimpleStringProperty bookPublisher;
    private SimpleIntegerProperty bookQuantity;
    private SimpleIntegerProperty bookRemaining;

    public Book(int id, String name, String author, String bookPublisher, int bookQuantity, int bookRemaining) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
        this.bookQuantity = new SimpleIntegerProperty(bookQuantity);
        this.bookRemaining = new SimpleIntegerProperty(bookRemaining);
    }

    public SimpleIntegerProperty getId() {
        return id;
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getAuthor() {
        return author;
    }

    public void setAuthor(SimpleStringProperty author) {
        this.author = author;
    }

    public SimpleStringProperty getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(SimpleStringProperty bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public SimpleIntegerProperty getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(SimpleIntegerProperty bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public SimpleIntegerProperty getBookRemaining() {
        return bookRemaining;
    }

    public void setBookRemaining(SimpleIntegerProperty bookRemaining) {
        this.bookRemaining = bookRemaining;
    }
}
