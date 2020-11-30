package com.yourcompany.api.factories;

public class UserFactory {
    private String name;
    private String password;
    private String email;
    private String address;
    private String zip;
    private String city;

    public boolean isValid ()  {
        if (name == null || name.isBlank()) return false;
        if(password == null || password.isBlank()) return false;
        if(email == null || email.isBlank()) return false;
        if(address == null || address.isBlank()) return false;
        if(zip == null || zip.isBlank()) return false;
        if(city == null || city.isBlank()) return false;
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
