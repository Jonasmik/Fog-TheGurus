package com.yourcompany.exceptions.bom;

public class NoSuchMaterialExist extends Exception {

    public NoSuchMaterialExist() {
    }

    public NoSuchMaterialExist(String message) {
        super(message);
    }

    public NoSuchMaterialExist(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMaterialExist(Throwable cause) {
        super(cause);
    }
}
