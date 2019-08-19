package com.codecool.web.dao;

import com.codecool.web.model.Role;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User findUserByEmail(String email) throws SQLException;
    User addUser(String name, String email, String password, Role role, int balance) throws SQLException;
    void updateUserBalance(User user, int credit) throws SQLException;
    List<User> findAll() throws SQLException;
    User findById(int id) throws SQLException;
    boolean findIfUserExists(String email) throws SQLException;




}
