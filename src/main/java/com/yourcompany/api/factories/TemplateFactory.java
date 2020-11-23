package com.yourcompany.api.factories;

public class TemplateFactory {

    /**
     * This is used for validation when creating something
     * Call isValid after using the set methods
     */

    private String example;

    public Boolean isValid() {
        if (this.example == null || this.example.equals("")) return false;
        return true;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }
}
