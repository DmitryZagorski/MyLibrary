package com.epam.library.models;

import java.util.Objects;

public class OrderBook {

    private int id;
    private int bookId;
    private String bookTitle;
    private int quantity;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        OrderBook orderBook = (OrderBook) o;
        return id == orderBook.id &&
                bookId == orderBook.bookId &&
                quantity == orderBook.quantity &&
                orderId == orderBook.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, quantity, orderId);
    }
}
