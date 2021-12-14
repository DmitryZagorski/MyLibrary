package com.epam.library.exceptions;

public class BookNotFoundException extends BookException {

    private Integer id;
    private String title;

    public BookNotFoundException(Integer id){
        this.id = id;
    }

    public BookNotFoundException(Integer id, Throwable e){
        super(e);
        this.id = id;
    }

    public BookNotFoundException(String title, Throwable e){
        super(e);
        this.title = title;
    }
}