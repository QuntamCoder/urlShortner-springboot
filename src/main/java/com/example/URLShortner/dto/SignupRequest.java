package com.example.URLShortner.dto;

public class SignupRequest {
    private String name;
    private  String email;
    private String password;

    public String getName() {
        return name;
    }

    public SignupRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignupRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignupRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
