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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate.get() == null) ? 0 : createDate.get().hashCode());
        result = prime * result + ((email.get() == null) ? 0 : email.get().hashCode());
        result = prime * result + ((extraPhone.get() == null) ? 0 : extraPhone.get().hashCode());
        result = prime * result + id.get();
        result = prime * result + ((name.get() == null) ? 0 : name.get().hashCode());
        result = prime * result + ((phone.get() == null) ? 0 : phone.get().hashCode());
        result = prime * result + ((photo.get() == null) ? 0 : photo.get().hashCode());
        result = prime * result + status.get();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Master other = (Master) obj;
        if (createDate.get() == null) {
            if (other.createDate.get() != null)
                return false;
        } else if (!createDate.get().equals(other.createDate.get()))
            return false;
        if (email.get() == null) {
            if (other.email.get() != null)
                return false;
        } else if (!email.get().equals(other.email.get()))
            return false;
        if (extraPhone.get() == null) {
            if (other.extraPhone.get() != null)
                return false;
        } else if (!extraPhone.get().equals(other.extraPhone.get()))
            return false;
        if (name.get() == null) {
            if (other.name.get() != null)
                return false;
        } else if (!name.get().equals(other.name.get()))
            return false;
        if (phone.get() == null) {
            if (other.phone.get() != null)
                return false;
        } else if (!phone.get().equals(other.phone.get()))
            return false;
        if (photo.get() == null) {
            if (other.photo.get() != null)
                return false;
        } else if (!photo.get().equals(other.photo.get()))
            return false;
        if (id.get() != other.id.get()) {
            return false;
        }
        if (status.get() != other.status.get()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}