package app.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;

    public Student(String id, String name, String address, String email, String phoneNumber) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty getIdProperty() {
        return id;
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty getAddressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty getPhoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    @Override
    public String toString() {
        return "Student [id=" + id.get() + ", name=" + name.get() + ", address=" + address.get() + ", email="
                + email.get() + ", phoneNumber="
                + phoneNumber.get() + "]";
    }
}
