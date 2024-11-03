package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Comment {
    private SimpleIntegerProperty id;
    private SimpleStringProperty userId;
    private SimpleStringProperty bookId;
    private SimpleStringProperty information;
    private SimpleStringProperty date;

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
        return userId.get();
    }

    public SimpleStringProperty getStudentIdProperty() {
        return userId;
    }

    public void setStudentId(String id) {
        userId = new SimpleStringProperty(id);
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

    public String getInformation() {
        return information.get();
    }

    public SimpleStringProperty getInformationProperty() {
        return information;
    }


    public void setInformation(String information) {
        this.information = new SimpleStringProperty(information);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty getDateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
}
