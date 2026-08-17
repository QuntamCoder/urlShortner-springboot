package com.example.URLShortner.dto;

import com.example.URLShortner.entity.Role;

import java.time.LocalDateTime;

public class AdminUrlResponse {

    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;
    private boolean active;
    private Long clickCount;

    private Long userId;
    private String userName;
    private String userEmail;

    public AdminUrlResponse() {
    }

    public AdminUrlResponse(
            Long id,
            String originalUrl,
            String shortUrl,
            LocalDateTime createdAt,
            LocalDateTime expirationDate,
            boolean active,
            Long clickCount,
            Long userId,
            String userName,
            String userEmail) {

        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.active = active;
        this.clickCount = clickCount;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}