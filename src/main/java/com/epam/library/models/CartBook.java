package com.epam.library.models;

import java.util.Date;

public class CartBook {

    private int bookId;
    private int bookQuantity;
    private int cartId;
    private java.util.Date startDate;
    private java.util.Date expirationDate;
    private PlaceOfReading placeOfReading;

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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PlaceOfReading getPlaceOfReading() {
        return placeOfReading;
    }

    public void setPlaceOfReading(PlaceOfReading placeOfReading) {
        this.placeOfReading = placeOfReading;
    }
}
