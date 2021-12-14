package com.epam.library.exceptions;

public class CustomerException extends RuntimeException {

    public CustomerException() {

    }

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable e) {
        super(message, e);
    }

    public CustomerException(Throwable e) {
        super(e);
    }

}
