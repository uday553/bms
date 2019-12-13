package com.bms.booking.exceptions;

public class FailedException extends Exception {
    public FailedException(String message, Throwable t) {
        super(message, t);
    }
}
