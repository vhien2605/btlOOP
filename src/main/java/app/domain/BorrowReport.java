package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowReport {
    private SimpleIntegerProperty id;
    private SimpleStringProperty studentId;
    private SimpleStringProperty bookId;
    private SimpleStringProperty borrowDate;
    private SimpleStringProperty returnDate;
    private SimpleStringProperty expectedReturnDate;
    private SimpleStringProperty status;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getStudentId() {
        return studentId.get();
    }

    public SimpleStringProperty getStudentIdProperty() {
        return studentId;
    }

    public void setStudentId(String id) {
        this.studentId = new SimpleStringProperty(id);
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
                ", studentId=" + studentId.get() +
                ", bookId=" + bookId.get() +
                ", borrowDate=" + borrowDate.get() +
                ", expectedReturnDate=" + expectedReturnDate.get() +
                ", returnDate=" + returnDate.get() +
                ", status=" + status.get() +
                '}';
    }
}
