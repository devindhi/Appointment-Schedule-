package com.example.demo3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Customer {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty lastname;
    private final StringProperty contact;
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty email;
    private final StringProperty type;

    public Customer(String id, String name, String lastname, String contact, String date, String time, String email, String type) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.contact = new SimpleStringProperty(contact);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public String getEmail() {
        return email.get();
    }

}
