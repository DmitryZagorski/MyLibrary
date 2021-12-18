package com.epam.library.models;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private int id;
    private int cartBookId;
    private int totalQuantity;
    private int customerId;
    private java.util.Date date;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartBookId() {
        return cartBookId;
    }

    public void setCartBookId(int cartBookId) {
        this.cartBookId = cartBookId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
