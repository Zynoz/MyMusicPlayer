package com.zynoz.exception;

public class MediaManagerException extends Exception {
    public MediaManagerException() {
        super();
    }

    public MediaManagerException(String message) {
        super(message);
    }

    public MediaManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaManagerException(Throwable cause) {
        super(cause);
    }

    protected MediaManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}