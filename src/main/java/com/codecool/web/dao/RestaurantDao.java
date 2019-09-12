package com.codecool.web.dao;

import com.codecool.web.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantDao {
    Restaurant addRestaurant(String name) throws SQLException;
    Restaurant findRestaurantByName(String name) throws SQLException;
    List<Restaurant> findAll() throws SQLException;
    Restaurant findRestaurantById(int id) throws SQLException;
    boolean findIfRestaurantExists(String name) throws SQLException;
}
