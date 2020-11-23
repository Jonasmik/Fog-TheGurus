package com.yourcompany.exceptions;

public class NoSuchTemplateExists extends Exception {

    public NoSuchTemplateExists(int id) {
        super("Template with id: " + id + ", could not be found");
    }

    public NoSuchTemplateExists(String message) {
        super(message);
    }
}
