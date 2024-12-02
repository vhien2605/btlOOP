package app.domain;

public class BorrowReport {
    public static final String PENDING = "Pending";
    public static final String BORROWED = "Borrowed";
    public static final String RETURNED = "Returned";

    private int id;
    private String userId;
    private String bookId;
    private String borrowDate;
    private String returnDate;
    private String expectedReturnDate;
    private String status;
    private String qrcodeUrl;

    public BorrowReport(int id, String userId, String bookId, String borrowDate, String returnDate,
            String expectedReturnDate, String status, String qrcodeUrl) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.expectedReturnDate = expectedReturnDate;
        this.status = status;
        this.qrcodeUrl = qrcodeUrl;
    }

    public BorrowReport(BorrowReport data) {
        this.id = data.id;
        this.userId = data.userId;
        this.bookId = data.bookId;
        this.borrowDate = data.borrowDate;
        this.returnDate = data.returnDate;
        this.expectedReturnDate = data.expectedReturnDate;
        this.status = data.status;
        this.qrcodeUrl = data.qrcodeUrl;
    }

    public static String getPending() {
        return PENDING;
    }

    public static String getBorrowed() {
        return BORROWED;
    }

    public static String getReturned() {
        return RETURNED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    @Override
    public String toString() {
        return "BorrowReport [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", borrowDate=" + borrowDate
                + ", returnDate=" + returnDate + ", expectedReturnDate=" + expectedReturnDate + ", status=" + status
                + ", qrcodeUrl=" + qrcodeUrl + "]";
    }

}
