package com.example.URLShortner.dto;

import com.example.URLShortner.entity.Role;

public class SignupResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public SignupResponse() {
    }

    public SignupResponse(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public SignupResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SignupResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignupResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public SignupResponse setRole(Role role) {
        this.role = role;
        return this;
    }
}
