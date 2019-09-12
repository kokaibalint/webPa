package com.codecool.web.service.simple;

import com.codecool.web.dao.RestaurantDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Restaurant;
import com.codecool.web.service.RestaurantService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleRestaurantService implements RestaurantService {
    final RestaurantDao restaurantDao;

    public SimpleRestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    public Restaurant addRestaurant(String name) throws SQLException {
        return restaurantDao.addRestaurant(name);
    }

    @Override
    public Restaurant findRestaurantByName(String name) throws SQLException {
        return restaurantDao.findRestaurantByName(name);
    }

    @Override
    public List<Restaurant> findAll() throws SQLException, ServiceException {
        return restaurantDao.findAll();
    }

    @Override
    public Restaurant findRestaurantById(int id) throws SQLException {
        return restaurantDao.findRestaurantById(id);
    }

    @Override
    public boolean findIfRestaurantExists(String name) throws SQLException {
        return restaurantDao.findIfRestaurantExists(name);
    }
}
