package com.example.URLShortner.dto;

import com.example.URLShortner.entity.Role;

public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private  String token;



    public LoginResponse() {
    }

    public LoginResponse(Long id, String name, String email, Role role,String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
    public Long getId() {
        return id;
    }

    public LoginResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public LoginResponse setRole(Role role) {
        this.role = role;
        return this;
    }
}
