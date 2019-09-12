package com.codecool.web.dao.database;

import com.codecool.web.dao.RestaurantDao;
import com.codecool.web.model.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DataBaseRestaurantDao extends AbstractDao implements RestaurantDao {
    public DataBaseRestaurantDao(Connection connection) {
        super(connection);
    }

    @Override
    public Restaurant addRestaurant(String name) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO restaurants (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new Restaurant(id, name);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public Restaurant findRestaurantByName(String name) throws SQLException {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        String sql = "SELECT id, name FROM restaurants WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchRestaurant(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<Restaurant> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Restaurant> restaurants = new ArrayList<>();
            while (resultSet.next()) {
                restaurants.add(fetchRestaurant(resultSet));
            }
            return restaurants;
        }
    }

    @Override
    public Restaurant findRestaurantById(int id) throws SQLException {
        String sql = "SELECT * FROM restaurants WHERE restaurant_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchRestaurant(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public boolean findIfRestaurantExists(String name) throws SQLException {
        String sql = "SELECT * FROM restaurants WHERE name=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    private Restaurant fetchRestaurant(ResultSet resultSet)throws SQLException{
        int id = resultSet.getInt("restaurant_id");
        String name = resultSet.getString("name");
        return new Restaurant(id, name);
    }
}
