package com.epam.library.exceptions;

public class CartException extends RuntimeException{

    public CartException() {
        super();
    }

    public CartException(String message) {
        super(message);
    }

    public CartException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartException(Throwable cause) {
        super(cause);
    }

    protected CartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
