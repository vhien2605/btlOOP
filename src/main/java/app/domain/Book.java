package app.domain;

public class Book {
    private String id;
    private String name;
    private String author;
    private String description;
    private String category;
    private String bookPublisher;
    private Integer bookQuantity;
    private Integer bookRemaining;
    private String imagePath;

    public Book(String id, String name, String author, String description, String category, String bookPublisher,
            int bookQuantity, int bookRemaining, String imagePath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.bookPublisher = bookPublisher;
        this.bookQuantity = bookQuantity;
        this.bookRemaining = bookRemaining;
        this.imagePath = imagePath;
    }

    public Book(String id, String name, String author, String description, String category, String bookPublisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.bookPublisher = bookPublisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Integer getBookRemaining() {
        return bookRemaining;
    }

    public void setBookRemaining(Integer bookRemaining) {
        this.bookRemaining = bookRemaining;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + ", description=" + description
                + ", category=" + category + ", bookPublisher=" + bookPublisher + ", bookQuantity=" + bookQuantity
                + ", bookRemaining=" + bookRemaining + ", imagePath=" + imagePath + "]";
    }
}
