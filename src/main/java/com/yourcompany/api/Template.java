package com.yourcompany.api;

import com.yourcompany.api.facades.TemplateFacade;

public class Template {

    /**
     * Generates the com.yourcompany.api, and is created in the ICommand class
     * 1. Set your programs current version here
     * 2. Implement your Facades in here
     */

    private static final String VERSION = "0.1";

    private final TemplateFacade templateFacade;

    public Template(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public TemplateFacade getTemplateFacade() {
        return templateFacade;
    }
}
