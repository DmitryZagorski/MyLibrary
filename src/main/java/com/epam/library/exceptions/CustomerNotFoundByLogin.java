package com.epam.library.exceptions;

public class CustomerNotFoundByLogin extends RuntimeException {

    public CustomerNotFoundByLogin() {
    }

    public CustomerNotFoundByLogin(String message) {
        super(message);
    }

    public CustomerNotFoundByLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundByLogin(Throwable cause) {
        super(cause);
    }

    public CustomerNotFoundByLogin(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
