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

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(SimpleStringProperty author) {
        this.author = author;
    }

    public String getBookPublisher() {
        return bookPublisher.get();
    }

    public void setBookPublisher(SimpleStringProperty bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public int getBookQuantity() {
        return bookQuantity.get();
    }

    public void setBookQuantity(SimpleIntegerProperty bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getBookRemaining() {
        return bookRemaining.get();
    }

    public void setBookRemaining(SimpleIntegerProperty bookRemaining) {
        this.bookRemaining = bookRemaining;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", author=" + author.get() +
                ", bookPublisher=" + bookPublisher.get() +
                ", bookQuantity=" + bookQuantity.get() +
                ", bookRemaining=" + bookRemaining.get() +
                '}';
    }
}
