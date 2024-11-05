package app.domain.DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportDetail {
    private SimpleIntegerProperty id;
    private SimpleStringProperty userName;
    private SimpleStringProperty bookName;
    private SimpleStringProperty borrowDate;
    private SimpleStringProperty returnDate;
    private SimpleStringProperty expectedReturnDate;
    private SimpleStringProperty status;

    public ReportDetail(int id, String userName, String bookName,
                        String borrowDate, String returnDate, String expectedReturnDate, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(userName);
        this.bookName = new SimpleStringProperty(bookName);
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

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty getUserNameProperty() {
        return userName;
    }

    public String getBookName() {
        return bookName.get();
    }

    public SimpleStringProperty getBookNameProperty() {
        return bookName;
    }


    public String getBorrowDate() {
        return borrowDate.get();
    }

    public SimpleStringProperty getBorrowDateProperty() {
        return borrowDate;
    }


    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty getReturnDateProperty() {
        return returnDate;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate.get();
    }

    public SimpleStringProperty getExpectedReturnDateProperty() {
        return expectedReturnDate;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty getStatusProperty() {
        return status;
    }
}

