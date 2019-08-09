package com.codecool.web.model;

public class User extends AbstractModel{

    private String username;
    private String email;
    private String password;
    private String country;
    private Boolean role;
    private int balance;

    public User(int id, String username, String email, String password, String country, Boolean role, int balance) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.country = country;
        this.role = role;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getRole() {
        return role;
    }

    public int getBalance() {
        return balance;
    }
}
