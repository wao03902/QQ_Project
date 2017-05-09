package com.ivisoft.salon.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Service {
    
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty price = new SimpleIntegerProperty();
    private IntegerProperty duration = new SimpleIntegerProperty();
    private ObjectProperty<Dictionary> type = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
    private IntegerProperty status = new SimpleIntegerProperty();
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

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price);
    }

    public Integer getDuration() {
        return duration.get();
    }

    public void setDuration(Integer duration) {
        this.duration.set(duration);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Dictionary getType() {
        return type.get();
    }

    public void setType(Dictionary type) {
        this.type.set(type);
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
    public String toString() {
        return name.get();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate.get() == null) ? 0 : createDate.hashCode());
        result = prime * result + ((description.get() == null) ? 0 : description.hashCode());
        result = prime * result + duration.get();
        result = prime * result + id.get();
        result = prime * result + ((name.get() == null) ? 0 : name.hashCode());
        result = prime * result + price.get();
        result = prime * result + status.get();
        result = prime * result + ((type.get() == null) ? 0 : type.hashCode());
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
        Service other = (Service) obj;
        if (createDate.get() == null) {
            if (other.createDate.get() != null)
                return false;
        } else if (!createDate.get().equals(other.createDate.get()))
            return false;
        if (description.get() == null) {
            if (other.description.get() != null)
                return false;
        } else if (!description.get().equals(other.description.get()))
            return false;
        if (duration.get() != other.duration.get())
            return false;
        if (id.get() != other.id.get())
                return false;
        if (name.get() == null) {
            if (other.name != null)
                return false;
        } else if (!name.get().equals(other.name.get()))
            return false;
        if (price.get() != other.price.get())
                return false;
        if (status.get() != other.status.get()) 
                return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.get().equals(other.type.get()))
            return false;
        return true;
    }
    

}