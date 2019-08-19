package com.codecool.web.service;

import com.codecool.web.model.Role;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void updateUserBalance(User user, int credit) throws SQLException;
    User findById(int id) throws SQLException;
    User addUser (String username,String email,String password,Role role, int balance)throws SQLException;
    boolean findIfUserExists(String email) throws SQLException;
    List<User> findAll() throws SQLException, ServiceException;
}

