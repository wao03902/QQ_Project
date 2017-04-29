package com.ivisoft.salon.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Dictionary> sex = new SimpleObjectProperty<>();
    private StringProperty phone = new SimpleStringProperty();
    private IntegerProperty status = new SimpleIntegerProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> createDate = new SimpleObjectProperty<>();
    
    public Integer getId() {
        return id.get();
    }
    
    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public String getName() {
        return name.get();
    }
    
    public void setName(String fullName) {
        this.name.set(fullName);
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public Dictionary getSex() {
        return sex.get();
    }

    public void setSex(Dictionary sex) {
        this.sex.set(sex);
    }

    public Integer getStatus() {
        return status.get();
    }

    public void setStatus(Integer status) {
        this.status.set(status);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDateTime getCreateDate() {
        return createDate.get();
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate.set(createDate);
    }
}