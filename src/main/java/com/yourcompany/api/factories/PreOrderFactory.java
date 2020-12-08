package com.yourcompany.api.factories;

import com.yourcompany.exceptions.order.PreOrderValidationError;

public class PreOrderFactory {
    /*
    id INT PRIMARY KEY AUTO_INCREMENT,
    customerid INT NOT NULL,
    salesmanid INT DEFAULT NULL,
    carportid INT NOT NULL,
     */

    private int customerId;
    private int carportId;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Boolean isValid() {
        if (this.customerId < 0) {
            return false;
        }
        if (this.carportId < 0) {
            return false;
        }
        return true;
    }

    public void setCustomerId(String number) throws PreOrderValidationError {
        try {
            setCustomerId(number);
        } catch (NumberFormatException e) {
            throw new PreOrderValidationError(e.getMessage());
        }
    }

    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }

    public void setCarportId(String number) throws PreOrderValidationError {
        try {
            setCarportId(number);
        } catch (NumberFormatException e) {
            throw new PreOrderValidationError(e.getMessage());
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarportId() {
        return carportId;
    }
}
