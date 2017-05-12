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
    private ObjectProperty<Master> master = new SimpleObjectProperty<>();
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

    public Master getMaster() {
        return master.get();
    }

    public void setMaster(Master master) {
        this.master.set(master);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client.get() == null) ? 0 : client.get().hashCode());
        result = prime * result + ((dateAndTime.get() == null) ? 0 : dateAndTime.get().hashCode());
        result = prime * result + discount.get();
        result = prime * result + duration.get();
        result = prime * result + id.get();
        result = prime * result + ((master.get() == null) ? 0 : master.get().hashCode());
        result = prime * result + price.get();
        result = prime * result + ((service.get() == null) ? 0 : service.get().hashCode());
        result = prime * result + status.get();
        result = prime * result + totalPrice.get();
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
        Visit other = (Visit) obj;
        if (client.get() == null) {
            if (other.client.get() != null)
                return false;
        } else if (!client.get().equals(other.client.get()))
            return false;
        if (dateAndTime.get() == null) {
            if (other.dateAndTime.get() != null)
                return false;
        } else if (!dateAndTime.get().equals(other.dateAndTime.get()))
            return false;
        
        if (discount.get() != other.discount.get()) {
                return false;
        }
        if (duration.get() != other.duration.get()) {
            return false;
        }
        if (id.get() != other.id.get()) {
            return false;
        }
        if (price.get() != other.price.get()) {
            return false;
        }
        if (status.get() != other.status.get()) {
            return false;
        }
        if (totalPrice.get() != other.totalPrice.get()) {
            return false;
        }
        if (master.get() == null) {
            if (other.master.get() != null)
                return false;
        } else if (!master.get().equals(other.master.get()))
            return false;
        if (service.get() == null) {
            if (other.service.get() != null)
                return false;
        } else if (!service.get().equals(other.service.get()))
            return false;
        return true;
    }
}
