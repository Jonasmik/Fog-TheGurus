package com.yourcompany.domain.customer;

public class Customer {
    private final int id;
    private final int userid;
    private final String name;
    private final String adress;
    private final int zipcode;
    private final String city;
    private final String email;
    private final String additional;

    public Customer(int id, int userid, String name, String adress, int zipcode, String city, String email, String additional) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.additional = additional;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", additional='" + additional + '\'' +
                '}';
    }
}
