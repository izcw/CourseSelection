package com.Pojo;

public class User {
    public  Integer UserId;
    public String UserName;
    public String Password;
    public String UserType;

    public User() {
    }

    public User(Integer userId, String userName, String password, String userType) {
        UserId = userId;
        UserName = userName;
        Password = password;
        UserType = userType;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
