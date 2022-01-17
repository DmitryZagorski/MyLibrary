package com.epam.library.models;

import java.util.Objects;

public class Catalog {

    private int id;
    private int bookId;
    private String bookTitle;
    private int totalQuantity;
    private int freeQuantity;

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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id &&
                bookId == catalog.bookId &&
                totalQuantity == catalog.totalQuantity &&
                freeQuantity == catalog.freeQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, totalQuantity, freeQuantity);
    }
}
