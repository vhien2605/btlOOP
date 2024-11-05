package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowReport {
    public static final String PENDING = "Pending";
    public static final String BORROWED = "Borrowed";
    public static final String RETURNED = "Returned";
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty userId;
    private SimpleStringProperty bookId;
    private SimpleStringProperty borrowDate;
    private SimpleStringProperty returnDate;
    private SimpleStringProperty expectedReturnDate;
    private SimpleStringProperty status;

    public BorrowReport(int id, String userId, String bookId, String borrowDate
            , String returnDate, String expectedReturnDate, String status
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.userId = new SimpleStringProperty(userId);
        this.bookId = new SimpleStringProperty(bookId);
        this.borrowDate = new SimpleStringProperty(borrowDate);
        this.returnDate = new SimpleStringProperty(returnDate);
        this.expectedReturnDate = new SimpleStringProperty(expectedReturnDate);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getUserId() {
        return userId.get();
    }

    public SimpleStringProperty getUserIdProperty() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = new SimpleStringProperty(id);
    }

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty getBookIdProperty() {
        return bookId;
    }

    public void setBookId(String id) {
        this.bookId = new SimpleStringProperty(id);
    }

    public String getBorrowDate() {
        return borrowDate.get();
    }

    public SimpleStringProperty getBorrowDateProperty() {
        return borrowDate;
    }

    public void setBorrowDate(String date) {
        this.borrowDate = new SimpleStringProperty(date);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty getReturnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(String date) {
        this.returnDate = new SimpleStringProperty(date);
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate.get();
    }

    public SimpleStringProperty getExpectedReturnDateProperty() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(String date) {
        this.expectedReturnDate = new SimpleStringProperty(date);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty getStatusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }

    @Override
    public String toString() {
        return "BorrowReport{" +
                "id=" + id.get() +
                ", userId=" + userId.get() +
                ", bookId=" + bookId.get() +
                ", borrowDate=" + borrowDate.get() +
                ", expectedReturnDate=" + expectedReturnDate.get() +
                ", returnDate=" + returnDate.get() +
                ", status=" + status.get() +
                '}';
    }
}
