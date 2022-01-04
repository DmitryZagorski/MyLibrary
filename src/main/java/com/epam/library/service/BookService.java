package com.epam.library.service;

import com.epam.library.models.Book;
import com.epam.library.repositories.JDBCBookRepository;

public class BookService {

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
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIssueDate(issueDate);
        //book.setGenreID(genreId);
        book.setGenreId(genreId);

        return JDBCBookRepository.getInstance().saveBook(book);

    }



}
