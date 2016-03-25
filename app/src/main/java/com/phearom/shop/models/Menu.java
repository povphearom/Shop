package com.phearom.shop.models;

/**
 * Created by phearom on 3/25/16.
 */
public class Menu {
    private Object key;
    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Menu() {
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
