package com.epam.library.repositories;

import com.epam.library.models.Book;
import com.epam.library.models.Customer;

import java.util.Collection;

public interface LibraryRepository {

    Collection<Customer> getAllCustomers();

    Collection<Book> getAllBooksInLibrary();

    Collection<Book> getFreeBooksInLibrary();

}
