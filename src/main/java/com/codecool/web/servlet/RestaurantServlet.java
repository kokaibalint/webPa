package com.codecool.web.servlet;

import com.codecool.web.dao.RestaurantDao;
import com.codecool.web.dao.database.DataBaseRestaurantDao;
import com.codecool.web.model.Restaurant;
import com.codecool.web.model.User;
import com.codecool.web.service.RestaurantService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleRestaurantService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RestaurantServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try (Connection connection = getConnection(req.getServletContext())) {
            RestaurantDao restaurantDao = new DataBaseRestaurantDao(connection);
            RestaurantService restaurantService = new SimpleRestaurantService(restaurantDao);
            int userId = user.getId();

            String name = req.getParameter("name");

            Restaurant restaurant = restaurantService.addRestaurant(name);

            sendMessage(resp, HttpServletResponse.SC_OK, restaurant);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
