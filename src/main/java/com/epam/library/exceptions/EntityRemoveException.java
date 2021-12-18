package com.epam.library.exceptions;

public class EntityRemoveException extends RuntimeException {

    public EntityRemoveException() {
    }

    public EntityRemoveException(String message) {
        super(message);
    }

    public EntityRemoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRemoveException(Throwable cause) {
        super(cause);
    }

    public EntityRemoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
