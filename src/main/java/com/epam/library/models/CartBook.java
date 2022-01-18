package com.epam.library.models;

import java.util.Objects;

public class CartBook {

    private int id;
    private int bookId;
    private String bookTitle;
    private int quantity;
    private int cartId;

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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBook cartBook = (CartBook) o;
        return id == cartBook.id &&
                bookId == cartBook.bookId &&
                quantity == cartBook.quantity &&
                cartId == cartBook.cartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, quantity, cartId);
    }
}
