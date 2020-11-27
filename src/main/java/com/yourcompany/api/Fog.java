package com.yourcompany.api;

import com.yourcompany.api.facades.CarportFacade;
import com.yourcompany.api.facades.UserFacade;

public class Fog {
    private static final String VERSION = "0.1";
    private final UserFacade userFacade;
    private final CarportFacade carportFacade;

    public Fog(UserFacade userFacade, CarportFacade carportFacade) {
        this.userFacade = userFacade;
        this.carportFacade = carportFacade;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public CarportFacade getCarportFacade() {
        return carportFacade;
    }
}
