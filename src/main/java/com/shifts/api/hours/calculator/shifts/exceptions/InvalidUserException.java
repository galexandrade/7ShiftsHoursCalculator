package com.shifts.api.hours.calculator.shifts.exceptions;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserException(Throwable cause) {
        super(cause);
    }

    public InvalidUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
