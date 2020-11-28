package com.yourcompany.api;

import com.yourcompany.api.facades.CarportFacade;
import com.yourcompany.api.facades.CustomerFacade;
import com.yourcompany.api.facades.ShedFacade;
import com.yourcompany.api.facades.UserFacade;

public class Fog {
    private static final String VERSION = "0.1";
    private final UserFacade userFacade;
    private final CarportFacade carportFacade;
    private final ShedFacade shedFacade;
    private final CustomerFacade customerFacade;

    public Fog(UserFacade userFacade, CarportFacade carportFacade, ShedFacade shedFacade, CustomerFacade customerFacade) {
        this.userFacade = userFacade;
        this.carportFacade = carportFacade;
        this.shedFacade = shedFacade;
        this.customerFacade = customerFacade;
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

    public ShedFacade getShedFacade() {
        return shedFacade;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

}
