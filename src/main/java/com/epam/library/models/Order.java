package com.epam.library.models;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private int id;
    private Customer customer;
    private Book book;
    private int bookQuantity;
    private PlaceOfReading placeOfReading;
    private Date startDate;
    private Date experienceDate;

    public Order(Customer customer, Book book, PlaceOfReading placeOfReading, int bookQuantity, Date startDate, Date experienceDate) {
        this.customer = customer;
        this.book = book;
        this.bookQuantity = bookQuantity;
        this.placeOfReading = placeOfReading;
        this.startDate = startDate;
        this.experienceDate = experienceDate;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public PlaceOfReading getPlaceOfReading() {
        return placeOfReading;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExperienceDate() {
        return experienceDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public void setPlaceOfReading(PlaceOfReading placeOfReading) {
        this.placeOfReading = placeOfReading;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setExperienceDate(Date experienceDate) {
        this.experienceDate = experienceDate;
    }
}
