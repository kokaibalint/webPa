package com.codecool.web.service.simple;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public class SimpleLoginService  implements LoginService {
    @Override
    public User loginUser(String email, String password) throws ServiceException, SQLException {
        return null;
    }
}
