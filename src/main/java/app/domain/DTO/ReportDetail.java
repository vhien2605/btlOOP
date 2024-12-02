package app.domain.DTO;

public class ReportDetail {
    private int id;
    private String userName;
    private String bookName;
    private String borrowDate;
    private String returnDate;
    private String expectedReturnDate;
    private String status;

    public ReportDetail(int id, String userName, String bookName,
            String borrowDate, String returnDate, String expectedReturnDate, String status) {
        this.id = id;
        this.userName = userName;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.expectedReturnDate = expectedReturnDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReportDetail [id=" + id + ", userName=" + userName + ", bookName=" + bookName + ", borrowDate="
                + borrowDate + ", returnDate=" + returnDate + ", expectedReturnDate=" + expectedReturnDate + ", status="
                + status + "]";
    }

}
