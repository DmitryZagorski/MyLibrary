package com.epam.library.models;

import java.sql.Date;
import java.util.Objects;

public class Order {

    private int id;
    private int totalQuantity;
    private int customerId;
    private Date creationDate;
    private Date expirationDate;
    private int placeOfReadingId;
    private int cartId;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getPlaceOfReadingId() {
        return placeOfReadingId;
    }

    public void setPlaceOfReadingId(Integer placeOfReadingId) {
        this.placeOfReadingId = placeOfReadingId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                totalQuantity == order.totalQuantity &&
                customerId == order.customerId &&
                placeOfReadingId == order.placeOfReadingId &&
                cartId == order.cartId &&
                active == order.active &&
                Objects.equals(creationDate, order.creationDate) &&
                Objects.equals(expirationDate, order.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalQuantity, customerId, creationDate, expirationDate, placeOfReadingId, cartId, active);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalQuantity=" + totalQuantity +
                ", customerId=" + customerId +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", placeOfReadingId=" + placeOfReadingId +
                ", cartId=" + cartId +
                ", active=" + active +
                '}';
    }
}
