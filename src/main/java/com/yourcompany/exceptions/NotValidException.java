package com.yourcompany.exceptions;

/**
 * Used in factories to validate
 */
public class NotValidException extends Exception {

    public NotValidException(String message) {
        super(message);
    }
}
