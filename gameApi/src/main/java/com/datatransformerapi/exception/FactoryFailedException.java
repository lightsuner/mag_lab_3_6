package com.datatransformerapi.exception;

public class FactoryFailedException extends Exception {
    public FactoryFailedException() {
    }

    public FactoryFailedException(String message) {
        super(message);
    }

    public FactoryFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryFailedException(Throwable cause) {
        super(cause);
    }

    public FactoryFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
