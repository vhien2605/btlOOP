package app.domain;

public class Comment {
    private int id;
    private String userId;
    private String bookId;
    private String information;
    private String date;

    public Comment(int id, String userId, String bookId, String information, String date) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.information = information;
        this.date = date;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
