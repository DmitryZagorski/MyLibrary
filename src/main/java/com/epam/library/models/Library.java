package com.epam.library.models;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<Book> books;
    private List<Customer> customers;

    public Library(String name) {
        this.name = name;
        books = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
