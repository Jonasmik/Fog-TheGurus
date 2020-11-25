package com.yourcompany.exceptions;

public class NoSuchUserExists extends Exception {
    public NoSuchUserExists(String name) {
        /*
        Called when creating a new user in Register, LogicFacade and UserHandler respectively
         */
        super("User already exists: " + name);
    }
}