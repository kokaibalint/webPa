package com.codecool.web.model;

public class Restaurant extends AbstractModel {

    private String name;

    public Restaurant(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
