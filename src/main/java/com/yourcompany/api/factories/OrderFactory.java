package com.yourcompany.api.factories;

import com.yourcompany.exceptions.order.OrderValidation;

public class OrderFactory {

    private int offer;
    private int customerId;

    public boolean isValid() {
        if(this.offer < 0) {
            return false;
        }
        if (this.customerId < 0) {
            return false;
        }
        return true;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setOffer(String number) throws OrderValidation {
        try {
            setOffer(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new OrderValidation(e.getMessage());
        }
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerId(String number) throws OrderValidation {
        try {
            setCustomerId(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new OrderValidation(e.getMessage());
        }
    }

    public int getOffer() {
        return offer;
    }

    public int getCustomerId() {
        return customerId;
    }
}
