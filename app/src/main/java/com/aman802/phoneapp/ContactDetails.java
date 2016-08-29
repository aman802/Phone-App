package com.aman802.phoneapp;

/**
 * Created by Aman Vangani on 20-07-2016.
 */
public class ContactDetails {

    private String name,number;

    public ContactDetails(String name,String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
