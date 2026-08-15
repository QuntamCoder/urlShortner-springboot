package com.example.URLShortner.dto;

import java.time.LocalDateTime;

public class CreateUrlRequest {

    private String originalUrl;
    private LocalDateTime expirationDate;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public CreateUrlRequest setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public CreateUrlRequest setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}