package com.example.services;

public class User {
    public String username,email,password,gender;

    public User(){

    }

    public User(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
