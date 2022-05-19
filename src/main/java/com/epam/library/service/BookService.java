package com.epam.library.service;

import com.epam.library.models.Book;
import com.epam.library.repositories.JDBCBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

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

    public Book addBook(String title, String author, Date issueDate, Integer genreId){
        Log.info("Setting of book values");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIssueDate(issueDate);
        book.setGenreId(genreId);
        return JDBCBookRepository.getInstance().saveBook(book);
    }

    public Integer getBookIdByBookTitle(String bookTitle){
        Book bookByTitle = JDBCBookRepository.getInstance().getBookByTitle(bookTitle);
        int bookId = bookByTitle.getId();
        List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
        for (Book book : allBooks) {
            if (book.getId()==bookId) {
                bookId = book.getId();
            }
        }
        return bookId;
    }

}
