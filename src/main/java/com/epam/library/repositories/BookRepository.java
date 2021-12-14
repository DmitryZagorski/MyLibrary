package com.epam.library.repositories;

import com.epam.library.models.Book;

import java.util.Collection;
import java.util.Date;

public interface BookRepository {

    void addBook(Book book, Integer quantity);

    void removeBookById(Integer id);

    void removeAllBooks();

    Book getBookById(Integer id);

    Collection<Book> getBooksByTitle(String title);

    Collection<Book> getBooksByAuthor(String author);

    Collection<Book> getBooksByDateOfIssue(Date dateOfIssue);

    Collection<Book> getAllBooks();

}
