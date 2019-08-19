package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Role;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleUserService implements UserService {
    private UserDao userDao;

    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void updateUserBalance(User user, int credit) throws SQLException {
        userDao.updateUserBalance(user, credit);
    }


    @Override
    public User findById(int id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    public User addUser(String username, String email, String password, Role role, int balance) throws SQLException {
        return userDao.addUser(username,email,password,role,balance);
    }


    @Override
    public boolean findIfUserExists(String email) throws SQLException {
        return userDao.findIfUserExists(email);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }
}
