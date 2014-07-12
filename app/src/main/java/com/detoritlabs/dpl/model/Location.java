package com.detoritlabs.dpl.model;

import java.util.List;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class Location {



    String name;
    String number;
    String address;
    List<Hour> hours;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }
}
