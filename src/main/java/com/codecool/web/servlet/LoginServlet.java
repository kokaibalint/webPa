package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DataBaseUserDao;
import com.codecool.web.model.Role;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;
import com.codecool.web.service.simple.SimpleUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())){
            UserDao userDao = new DataBaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);
            LoginService loginService = new SimpleLoginService();

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = loginService.loginUser(email,password);

            req.setAttribute("user", user);

            sendMessage(resp, HttpServletResponse.SC_OK, user);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
