package com.codecool.web.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return price == food.price &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price);
    }
}
