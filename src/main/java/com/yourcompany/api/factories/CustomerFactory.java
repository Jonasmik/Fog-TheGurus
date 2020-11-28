package com.yourcompany.api.factories;

import com.yourcompany.exceptions.user.CustomerValidation;

public class CustomerFactory {

    private int userid;
    private String name;
    private String adress;
    private int zipcode;
    private String city;
    private String email;
    private String additional;

    public Boolean isValid() {
        if(this.userid < 0) return false;
        if(this.name == null || this.name.isBlank()) return false;
        if(this.adress == null || this.adress.isBlank()) return false;
        if(this.zipcode < 0) return false;
        if(this.city == null || this.city.isBlank()) return false;
        if(this.email == null || this.email.isBlank()) return false;

        return true;
    }


    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUserid(String number) throws CustomerValidation {
        try {
            setUserid(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new CustomerValidation(e.getMessage());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setZipcode(String number) throws CustomerValidation {
        try {
            setZipcode(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new CustomerValidation(e.getMessage());
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public int getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditional() {
        return additional;
    }
}
