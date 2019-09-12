package com.codecool.web.service;

import com.codecool.web.model.Restaurant;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(String name) throws SQLException;
    Restaurant findRestaurantByName(String name) throws SQLException;
    List<Restaurant> findAll() throws SQLException, ServiceException;
    Restaurant findRestaurantById(int id) throws SQLException;
    boolean findIfRestaurantExists(String name) throws SQLException;
}
