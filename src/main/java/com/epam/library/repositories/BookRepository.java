package com.epam.library.repositories;

import com.epam.library.models.Book;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface BookRepository {

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByDateOfIssue(Date dateOfIssue);

}
