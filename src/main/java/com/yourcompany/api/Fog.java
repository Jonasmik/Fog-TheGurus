package com.yourcompany.api;

import com.yourcompany.api.facades.UserFacade;

public class Fog {
    private static final String VERSION = "0.1";
    private final UserFacade userFacade;

    public Fog(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }
}
