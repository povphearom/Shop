package com.phearom.shop.models;

/**
 * Created by phearom on 3/27/16.
 */
public class Card {
    private String id;
    private String number;
    private String serial;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
