package com.epam.library.service;

import com.epam.library.models.Book;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCPlaceOfReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {

    private static final Logger Log = LoggerFactory.getLogger(BookService.class);

    private static BookService instance;

    private BookService(){
        instance = this;
    }

    public static BookService getInstance(){
        if (instance==null){
            BookService.instance = new BookService();
        }
        return instance;
    }

    public Book addBook(String title, String author, java.sql.Date issueDate, Integer genreId){
        Log.info("Setting of book values");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIssueDate(issueDate);
        book.setGenreId(genreId);
        return JDBCBookRepository.getInstance().saveBook(book);
    }
}
