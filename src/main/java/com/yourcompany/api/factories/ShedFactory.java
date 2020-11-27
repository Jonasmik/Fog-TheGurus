package com.yourcompany.api.factories;

import com.yourcompany.exceptions.shed.ShedValidations;

public class ShedFactory {
    private int length;
    private int width;
    private int carportID;

    public boolean isValid() {
        if (length <= 0) return false;
        if (width <= 0) return false;
        if (carportID <= 0) return false;
        return true;
    }




    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLength(String number) throws ShedValidations {
        try{
            setLength(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new ShedValidations(e.getMessage());
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWidth(String number) throws ShedValidations {
        try{
            setWidth(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new ShedValidations(e.getMessage());
        }
    }

    public int getCarportID() {
        return carportID;
    }



    public void setCarportID(int carportID) {
        this.carportID = carportID;
    }

    public void setCarportID(String number) throws ShedValidations {
        try{
            setCarportID(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new ShedValidations(e.getMessage());
        }
    }

}
