package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty author;
    private SimpleStringProperty description;
    private SimpleStringProperty category;
    private SimpleStringProperty bookPublisher;
    private SimpleIntegerProperty bookQuantity;
    private SimpleIntegerProperty bookRemaining;
    private SimpleStringProperty imagePath;

    public Book(String id, String name, String author, String description, String category, String bookPublisher,
                int bookQuantity, int bookRemaining, String imagePath) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
        this.bookQuantity = new SimpleIntegerProperty(bookQuantity);
        this.bookRemaining = new SimpleIntegerProperty(bookRemaining);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    public Book(String id, String name, String author, String description, String category, String bookPublisher) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty getIdProperty() {
        return id;
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty getAuthorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = new SimpleStringProperty(author);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = new SimpleStringProperty(desc);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty getCategoryProperty() {
        return category;
    }

    public void setCategory(String newCate) {
        category = new SimpleStringProperty(newCate);
    }

    public String getBookPublisher() {
        return bookPublisher.get();
    }

    public SimpleStringProperty getBookPublisherProperty() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
    }

    public int getBookQuantity() {
        return bookQuantity.get();
    }

    public SimpleIntegerProperty getBookQuantityProperty() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = new SimpleIntegerProperty(bookQuantity);
    }

    public int getBookRemaining() {
        return bookRemaining.get();
    }

    public SimpleIntegerProperty getBookRemainingProperty() {
        return bookRemaining;
    }

    public void setBookRemaining(int bookRemaining) {
        this.bookRemaining = new SimpleIntegerProperty(bookRemaining);
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public SimpleStringProperty getImagePathProperty() {
        return imagePath;
    }

    public void setImagePath(String newImagePath) {
        this.imagePath = new SimpleStringProperty(newImagePath);
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
