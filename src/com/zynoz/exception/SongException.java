package com.zynoz.exception;

public class SongException extends Exception {
    public SongException() {
        super();
    }

    public SongException(String message) {
        super(message);
    }

    public SongException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongException(Throwable cause) {
        super(cause);
    }

    protected SongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
