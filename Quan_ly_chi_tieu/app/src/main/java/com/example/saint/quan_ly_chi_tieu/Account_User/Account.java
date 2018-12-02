package com.example.saint.quan_ly_chi_tieu.Account_User;

public class Account {
    private String User,Password;

    public Account(String user, String password) {
        User = user;
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
