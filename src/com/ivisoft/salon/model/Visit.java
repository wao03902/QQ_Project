package com.ivisoft.salon.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Visit {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<Client> client = new SimpleObjectProperty<>();
    private ObjectProperty<Service> service = new SimpleObjectProperty<>();
    private IntegerProperty price = new SimpleIntegerProperty();
    private IntegerProperty duration = new SimpleIntegerProperty();
    private IntegerProperty discount = new SimpleIntegerProperty();
    private IntegerProperty totalPrice = new SimpleIntegerProperty();
    private ObjectProperty<LocalDateTime> dateAndTime = new SimpleObjectProperty<>();
    private IntegerProperty status = new SimpleIntegerProperty();
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Client getClient() {
        return client.get();
    }

    public void setClient(Client client) {
        this.client.set(client);
    }

    public Service getService() {
        return service.get();
    }

    public void setService(Service service) {
        this.service.set(service);
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

    public Integer getDiscount() {
        return discount.get();
    }

    public void setDiscount(Integer discount) {
        this.discount.set(discount);
    }

    public Integer getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime.get();
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime.set(dateAndTime);
    }

    public Integer getStatus() {
        return status.get();
    }

    public void setStatus(Integer status) {
        this.status.set(status);
    }
}
