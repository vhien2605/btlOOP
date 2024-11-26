package app.domain.DTO;

public class CommentDTO {
    private int id;
    private String username;
    private String email;
    private String bookId;
    private String information;
    private String date;
    
    public CommentDTO(int id, String username, String email, String bookId, String information, String date) {
        this.id = id;
        this.username = username;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
