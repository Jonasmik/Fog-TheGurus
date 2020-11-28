package com.yourcompany.exceptions.order;

public class NoSuchPreOrderExists extends Exception {
    public NoSuchPreOrderExists (String message) {
        super(message);
    }
}
