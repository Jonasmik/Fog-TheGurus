package com.yourcompany.api.factories;

import com.yourcompany.exceptions.user.SalesmanValidation;

public class SalesmanFactory {

    private int userId;

    public Boolean isValid() {
        if (this.userId < 0) {
            return false;
        }
        return true;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserId(String number) throws SalesmanValidation {
        try {
            setUserId(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new SalesmanValidation(e.getMessage());
        }
    }

    public int getUserId() {
        return userId;
    }
}
