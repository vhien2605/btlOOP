package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty author;
    private SimpleStringProperty description;
    private SimpleStringProperty category;
    private SimpleStringProperty bookPublisher;
    private SimpleIntegerProperty bookQuantity;
    private SimpleIntegerProperty bookRemaining;
    private SimpleStringProperty imagePath;

    public Book(int id, String name, String author, String description, String category, String bookPublisher,
            int bookQuantity, int bookRemaining, String imagePath) {
        this.id = new SimpleIntegerProperty(id);
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
        // Chỗ này sau sửa id vì id lấy từ api là string...
        this.id = new SimpleIntegerProperty(Integer.valueOf(id.substring(7)));
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
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

    public String getDescription() {
        return description.get();
    }

    public void setDescription(SimpleStringProperty desc) {
        this.description = desc;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(SimpleStringProperty newCate) {
        category = newCate;
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

    public String getImagePath() {
        return imagePath.get();
    }

    public void setImagePath(SimpleStringProperty newImagePath) {
        this.imagePath = newImagePath;
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
