package com.epam.library.exceptions;

public class CartBookException extends RuntimeException{

    public CartBookException() {
    }

    public CartBookException(String message) {
        super(message);
    }

    public CartBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartBookException(Throwable cause) {
        super(cause);
    }

    public CartBookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
