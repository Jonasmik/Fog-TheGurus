package com.yourcompany.api.factories;

import com.yourcompany.exceptions.CarportValidations;

import javax.validation.ValidationException;

public class CarportFactory {
    private int length;
    private int width;
    private String roof;
    private int roofAngle;

    public boolean isValid() {
        if (length <= 0) return false;
        if (width <= 0) return false;
        if (roof == null || roof.isBlank()) return false;
        if (roofAngle <= 0) return false;
        return true;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public void setLength(String number) throws CarportValidations {
        try{
            setLength(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new CarportValidations(e.getMessage());
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setWidth(String number) throws CarportValidations {
        try{
            setWidth(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new CarportValidations(e.getMessage());
        }
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public void setRoofAngle(int roofAngle) {
        this.roofAngle = roofAngle;
    }

    public void setRoofAngle(String number) throws CarportValidations {
        try{
            setRoofAngle(Integer.parseInt(number));
        } catch (NumberFormatException e){
            throw new CarportValidations(e.getMessage());
        }
    }

}


