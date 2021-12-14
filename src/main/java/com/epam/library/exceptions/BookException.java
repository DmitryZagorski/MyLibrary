package com.epam.library.exceptions;

public class BookException extends RuntimeException{

    public BookException() {

    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable e) {
        super(message, e);
    }

    public BookException(Throwable e) {
        super(e);
    }


}
