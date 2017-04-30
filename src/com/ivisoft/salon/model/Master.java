package com.ivisoft.salon.model;

import java.io.File;
import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Master {
    
    private ObjectProperty<File> photo = new SimpleObjectProperty<>();
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty extraPhone = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private IntegerProperty status = new SimpleIntegerProperty();
    private ObjectProperty<LocalDateTime> createDate = new SimpleObjectProperty<>();
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public File getPhoto() {
        return photo.get();
    }
    
    public void setPhoto(File photo) {
        this.photo.set(photo);
    }
    
    public String getName() {
        return name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public String getExtraPhone() {
        return extraPhone.get();
    }
    
    public void setExtraPhone(String extraPhone) {
        this.extraPhone.set(extraPhone);
    }
    
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public LocalDateTime getCreateDate() {
        return createDate.get();
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate.set(createDate);
    }

    public Integer getStatus() {
        return status.get();
    }

    public void setStatus(Integer status) {
        this.status.set(status);
    }
}