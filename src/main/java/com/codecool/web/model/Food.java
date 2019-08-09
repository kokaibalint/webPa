package com.codecool.web.model;

public class Food extends AbstractModel {

    private String name;
    private int price;

    public Food(int id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
