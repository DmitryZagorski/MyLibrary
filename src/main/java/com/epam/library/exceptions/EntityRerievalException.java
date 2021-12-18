package com.epam.library.exceptions;

public class EntityRerievalException extends RuntimeException {

    public EntityRerievalException() {
    }

    public EntityRerievalException(String message) {
        super(message);
    }

    public EntityRerievalException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRerievalException(Throwable cause) {
        super(cause);
    }

    public EntityRerievalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
