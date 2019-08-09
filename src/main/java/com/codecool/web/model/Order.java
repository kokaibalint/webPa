package com.codecool.web.model;

public class Order extends AbstractModel {

    private int userId;
    private int restaurantId;
    private int foodId;

    public Order(int id, int userId, int restaurantId, int foodId) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getFoodId() {
        return foodId;
    }
}
