package com.yourcompany.api;

import com.yourcompany.api.facades.*;

public class Fog {
    private static final String VERSION = "1.0";
    private final UserFacade userFacade;
    private final CarportFacade carportFacade;
    private final ShedFacade shedFacade;
    private final CustomerFacade customerFacade;
    private final PreOrderFacade preOrderFacade;
    private final SalesmanFacade salesmanFacade;
    private final MaterialPriceFacade materialPriceFacade;
    private final OfferFacade offerFacade;
    private final OrderFacade orderFacade;

    public Fog(UserFacade userFacade, CarportFacade carportFacade, ShedFacade shedFacade, CustomerFacade customerFacade,
        PreOrderFacade preOrderFacade, SalesmanFacade salesmanFacade,
        MaterialPriceFacade materialPriceFacade, OfferFacade offerFacade, OrderFacade orderFacade) {
        this.userFacade = userFacade;
        this.carportFacade = carportFacade;
        this.shedFacade = shedFacade;
        this.customerFacade = customerFacade;
        this.preOrderFacade = preOrderFacade;
        this.salesmanFacade = salesmanFacade;
        this.materialPriceFacade = materialPriceFacade;
        this.offerFacade = offerFacade;
        this.orderFacade = orderFacade;
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

    public PreOrderFacade getPreOrderFacade() {
        return preOrderFacade;
    }

    public SalesmanFacade getSalesmanFacade() {
        return salesmanFacade;
    }

    public MaterialPriceFacade getMaterialPriceFacade() {
        return materialPriceFacade;
    }

    public OfferFacade getOfferFacade() {
        return offerFacade;
    }

    public OrderFacade getOrderFacade() {
        return orderFacade;
    }
}
