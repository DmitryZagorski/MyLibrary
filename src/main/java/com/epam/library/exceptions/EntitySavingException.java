package com.epam.library.exceptions;

public class EntitySavingException extends RuntimeException {

    public EntitySavingException() {
    }

    public EntitySavingException(String message) {
        super(message);
    }

    public EntitySavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitySavingException(Throwable cause) {
        super(cause);
    }

    public EntitySavingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
