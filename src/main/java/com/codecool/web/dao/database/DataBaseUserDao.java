package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Role;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DataBaseUserDao extends AbstractDao implements UserDao {
    public DataBaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT id, user_name, user_email, user_password, user_role,user_balance FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public User addUser(String username, String email, String password, Role role, int balance) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (user_name, user_email, user_password, user_role, user_balance) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, role.getValue().toUpperCase());
            statement.setInt(5, balance);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new User(id, username, email, password, role,balance);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void updateUserBalance(User user, int credit) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET user_balance=user_balance+? WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, credit);
            statement.setInt(2, user.getId());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }

    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public boolean findIfUserExists(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_email=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;

    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String username = resultSet.getString("user_name");
        String email = resultSet.getString("user_email");
        String password = resultSet.getString("user_password");
        Role role = Role.valueOf(resultSet.getString("user_role"));
        int balance = resultSet.getInt("user_balance".toUpperCase());
        return new User(id, username, email, password, role, balance);
    }

}
