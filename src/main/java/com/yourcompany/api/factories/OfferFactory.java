package com.yourcompany.api.factories;

import com.yourcompany.exceptions.order.OfferValidationError;

public class OfferFactory {
    private int preorderid;
    private double price;
    private boolean active;

    public boolean isValid() {
        if (this.preorderid < 0){
            return false;
        }
        if (this.price < 0){
            return false;
        }
        return true;
    }

    public int getPreorderid() {
        return preorderid;
    }

    public void setPreorderid(int preorderid) {
        this.preorderid = preorderid;
    }
    public void setPreorderid(String preorderid) throws OfferValidationError {
        try {
            setPreorderid(Integer.parseInt(preorderid));
        } catch (NumberFormatException e) {
            throw new OfferValidationError(e.getMessage());
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setPrice(String price) throws OfferValidationError {
        try {
            setPrice(Double.parseDouble(price));
        } catch (NumberFormatException e) {
            throw new OfferValidationError(e.getMessage());
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
