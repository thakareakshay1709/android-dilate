package com.example.dilate;

public class UserModel {

    private String userId;
    private String email;
    private String password;

    public UserModel()
    {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserModel(String email, String pass, String userId)
    {
        this.email = email;
        this.password = pass;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
