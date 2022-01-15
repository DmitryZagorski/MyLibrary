package com.epam.library.models;

import java.util.Objects;

public class Cart {

    private int id;
    private int bookId;
    private int bookQuantity;
    private int CustomerId;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id &&
                bookId == cart.bookId &&
                bookQuantity == cart.bookQuantity &&
                CustomerId == cart.CustomerId &&
                orderId == cart.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, bookQuantity, CustomerId, orderId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookQuantity=" + bookQuantity +
                ", CustomerId=" + CustomerId +
                ", orderId=" + orderId +
                '}';
    }
}
