package com.example.URLShortner.dto;

import java.time.LocalDateTime;

public class UrlResponse {

    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;
    private boolean active;
    private Long clickCount;

    public UrlResponse() {
    }

    public UrlResponse(Long id, String originalUrl, String shortUrl,
                       LocalDateTime createdAt,
                       LocalDateTime expirationDate,
                       boolean active,
                       Long clickCount) {

        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.active = active;
        this.clickCount = clickCount;
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
}